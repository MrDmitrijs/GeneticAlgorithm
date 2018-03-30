$(document).ready(function () {

    var dataSurface = {
        x: JSON.parse($('#xValue').val()),
        y: JSON.parse($('#yValue').val()),
        z: JSON.parse($('#zValue').val()),
        type: 'surface'
    };

    var layout = {
        title: 'Result',
        showlegend: false,
        autosize: false,
        width: 500,
        height: 500,
        margin: {
            l: 50,
            r: 50,
            b: 65,
            t: 90
        }
    };
    Plotly.newPlot('surface', [dataSurface], layout);

    var DATA_TABLE = $('#table').DataTable({
        searching: false,
        info: false,
        columns: [
            {title: "Nr."},
            {title: "Code"},
            {title: "X"},
            {title: "Y"},
            {title: "Fitness"}
        ]
    });

    var DATA_TABLE_SUMMARY = $('#resultTable').DataTable({
        searching: false,
        info: false,
        columns: [
            {title: "Step"},
            {title: "AverageFitnessFunction"}
        ]
    });


    var updateResultTable = function (step, dataResultAverageFitness) {
        DATA_TABLE_SUMMARY.row.add([step, dataResultAverageFitness]);
        DATA_TABLE_SUMMARY.draw();
    };

    var updateResultGraph = function (step, dataResultAverageFitness) {
        var AverageFitness = {
            x: dataResultAverageFitness,
            y: step,
            type: 'scatter'
        };

        var data = [AverageFitness];

        Plotly.newPlot('graph', data);
    };

    var updateTableAndChart = function (data) {
        DATA_TABLE.clear();
        var data_x = [];
        var data_y = [];
        var data_z = [];
        var i = 1;
        var list = data.linkedDetailsList;
        list.forEach(function (value) {
            DATA_TABLE.row.add([
                i,
                value.fullBinaryCode,
                value.valueOfX,
                value.valueOfY,
                value.valueOfFitnessFunction
            ]);
            data_x.push(value.valueOfX);
            data_y.push(value.valueOfY);
            data_z.push(value.valueOfFitnessFunction);
            i++;
        });
        DATA_TABLE.draw();
        var trace1 = {
            x: data_x,
            y: data_y,
            z: data_z,
            mode: 'markers',
            marker: {
                color: 'rgb(0,0,0)',
                size: 4,
                opacity: 0.4
            },
            type: 'scatter3d'
        };
        Plotly.newPlot('surface', [dataSurface, trace1], layout);

    };

    $('#InitializeButton').on('click', function () {
        $.ajax({
            url: 'initializePopulation',
            data: {
                numberOfPopulation: $('#numberOfPopulation').val()
            },
            success: function (data) {
                updateTableAndChart(data);
                DATA_TABLE_SUMMARY.clear();
            }
        })
    });

    var startGeneration = function () {
        var endFunction = false;
        var step = 1;
        var resultDataX = [];
        var resultDataY = [];
        var dataK;
        while (!endFunction) {
            $.ajax({
                url: 'startGeneration',
                async: false,
                data: {
                    pCrossing: $('#pCrossing').val(),
                    pMutation: $('#pMutation').val(),
                    numberOfPopulation: $('#numberOfPopulation').val()
                },
                success: function (data) {
                    // updateTableAndChart(data);
                    // endFunction = data.endFunction;
                    resultDataX.push(data.averageFitnessFunction);
                    resultDataY.push(step);
                    dataK = data;
                    endFunction = data.endFunction;
                    updateResultTable(step, data.averageFitnessFunction);
                }
            });
            step++;
        }
        updateTableAndChart(dataK);
        updateResultGraph(resultDataX, resultDataY);
    };

    $('#startGeneration').on('click', function () {
        startGeneration();
    });
})
;