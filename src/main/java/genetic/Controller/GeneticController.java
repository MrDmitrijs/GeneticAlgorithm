package genetic.Controller;

import genetic.Common.Details;
import genetic.Common.EvolutionDetails;
import genetic.Common.GeneticParameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

import static genetic.Common.Constants.*;
import static genetic.facade.Evolution.initializeTable;
import static genetic.facade.Evolution.makeAnEvolution;
import static genetic.facade.FunctionManager.getResultOfFitnessFunction;
import static genetic.helper.Helper.getAverageFitnessFunction;
import static genetic.helper.Helper.roundToTwoDecimal;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

@Controller
public class GeneticController {

    @RequestMapping("/")
    public String welcome(final Model model) {
        initializeChart(model);
        model.addAttribute("geneticParameters", new GeneticParameters());
        return "MainPage";
    }

    @RequestMapping("/initializePopulation")
    @ResponseBody
    public EvolutionDetails getSomething(final HttpServletRequest httpServletRequest) {
        final int numberOfPopulation = parseInt(httpServletRequest.getParameter("numberOfPopulation"));
        final LinkedList<Details> linkedLists = initializeTable(numberOfPopulation);
        final EvolutionDetails evolutionDetails = new EvolutionDetails();
        evolutionDetails.setLinkedDetailsList(linkedLists);
        evolutionDetails.setEndFunction(false);
        evolutionDetails.setAverageFitnessCount(1);
        evolutionDetails.setAverageFitnessFunction(getAverageFitnessFunction(linkedLists));
        saveIntoSession(httpServletRequest, evolutionDetails);
        return evolutionDetails;
    }

    @RequestMapping("/startGeneration")
    @ResponseBody
    public EvolutionDetails startGeneration(final HttpServletRequest httpServletRequest) {
        final EvolutionDetails evolutionDetailsFromTheSession = getPopulationFromTheSession(httpServletRequest);
        final GeneticParameters geneticParameters = new GeneticParameters(parseInt(httpServletRequest.getParameter("numberOfPopulation")),
                parseDouble(httpServletRequest.getParameter("pCrossing")), parseDouble(httpServletRequest.getParameter("pMutation")));
        final LinkedList<Details> details = makeAnEvolution(evolutionDetailsFromTheSession.getLinkedDetailsList(), geneticParameters);
        evolutionDetailsFromTheSession.setLinkedDetailsList(details);
        final double newAverageFitnessFunction = getAverageFitnessFunction(details);
        double delta = roundToTwoDecimal(newAverageFitnessFunction - evolutionDetailsFromTheSession.getAverageFitnessFunction());

        if ((delta < 0 ? delta * -1 : delta) <= 0.1) {
            final int averageFitnessCount = evolutionDetailsFromTheSession.getAverageFitnessCount();
            if (averageFitnessCount >= 3) {
                evolutionDetailsFromTheSession.setEndFunction(true);
            } else {
                evolutionDetailsFromTheSession.setAverageFitnessCount(evolutionDetailsFromTheSession.getAverageFitnessCount() + 1);
            }
        }
        evolutionDetailsFromTheSession.setAverageFitnessFunction(newAverageFitnessFunction);
        saveIntoSession(httpServletRequest, evolutionDetailsFromTheSession);
        return evolutionDetailsFromTheSession;
    }


    ///private methods

    private EvolutionDetails getPopulationFromTheSession(final HttpServletRequest httpServletRequest) {
        final HttpSession session = httpServletRequest.getSession();
        return (EvolutionDetails) session.getAttribute(SESSION_ATTRIBUTE);
    }

    private void saveIntoSession(final HttpServletRequest httpServletRequest, final EvolutionDetails evolutionDetails) {
        final HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SESSION_ATTRIBUTE, evolutionDetails);
    }


    private void initializeChart(final Model model) {

        final LinkedList<LinkedList<Double>> zList = new LinkedList<>();

        for (double x = startX; x <= endX; x += step) {
            final LinkedList<Double> zArrayList = new LinkedList<>();
            for (double y = startY; y <= endY; y += step) {
                zArrayList.add(getResultOfFitnessFunction(x, y));
            }
            zList.add(zArrayList);
        }

        final LinkedList<LinkedList<Double>> finalZList = new LinkedList<>();
        for (int i = 0; i < zList.size(); i++) {
            final LinkedList<Double> zArrayList = new LinkedList<>();
            for (LinkedList<Double> arrayList : zList) {
                zArrayList.add(arrayList.get(i));
            }
            finalZList.add(zArrayList);
        }

        model.addAttribute("X", xList);
        model.addAttribute("Y", yList);
        model.addAttribute("Z", finalZList);
    }
}
