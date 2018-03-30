<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.16/datatables.min.css"/>
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.16/datatables.min.js"></script>
</head>
<body>

<h1>Genetic Algorithm</h1>

<div class="container">

    <div class="row" style="margin-bottom: 20px">
        <div class="col-md-4">
            <label for="numberOfPopulation">Number of population</label>
            <input type="text" id="numberOfPopulation" value="${geneticParameters.populationNumber}">
        </div>
        <div class="col-md-4">
            <label for="pCrossing">pCrossing</label>
            <input type="text" id="pCrossing" value="${geneticParameters.pCrossing}">
        </div>
        <div class="col-md-4">
            <label for="pMutation">pMutation</label>
            <input type="text" id="pMutation" value="${geneticParameters.pMutation}">
        </div>
    </div>
    <div class="row" style="margin-bottom: 20px">
        <div class="col-md-6">
            <button type="button" class="btn btn-primary" id="InitializeButton">Initialize</button>
        </div>
        <div class="col-md-6">
            <button type="button" class="btn btn-default" id="startGeneration">Run algorithm</button>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div id="surface"></div>
        </div>
        <div class="col-md-6">
            <table class="table table-bordered" id="table">
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6" >
            <div id="graph"></div>
        </div>
        <div class="col-md-6">
            <table class="table table-bordered" id="resultTable">
            </table>
        </div>
    </div>



    <input type="hidden" value="${X}" id="xValue">
    <input type="hidden" value="${Y}" id="yValue">
    <input type="hidden" value="${Z}" id="zValue">

    <script src="<c:url value="/js/main.js"/>"></script>
</div>
</body>
</html>