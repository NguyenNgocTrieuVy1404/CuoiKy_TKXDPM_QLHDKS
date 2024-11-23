package avgAmountDisplayKS.UseCase;

import java.util.List;

public class AvgAmountDisplayUseCase implements AvgAmountDisplayInputBoundary {
    private final AvgAmountDisplayDatabaseBoundary databaseBoundary;
    private final AvgAmountDisplayOutputBoundary outputBoundary;

    public AvgAmountDisplayUseCase(AvgAmountDisplayDatabaseBoundary databaseBoundary,
                                  AvgAmountDisplayOutputBoundary outputBoundary) {
        this.databaseBoundary = databaseBoundary;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute() {
        List<String> monthList = databaseBoundary.getMonthList();
        AvgAmountDisplayOutputDTO outputDTO = new AvgAmountDisplayOutputDTO(monthList);
        outputBoundary.presentMonthList(outputDTO);
    }
}
