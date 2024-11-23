package findInvoiceDisplayKS.UseCase;

import java.util.List;

public class FindDisplayUseCase implements FindDisplayInputBoundary {

    private FindDisplayDatabaseBoundary findDisplayDatabaseBoundary;
    private FindDisplayOutputBoundary findDisplayOutputBoundary;


    public FindDisplayUseCase(FindDisplayDatabaseBoundary findDisplayDatabaseBoundary,
            FindDisplayOutputBoundary findDisplayOutputBoundary) {
        this.findDisplayDatabaseBoundary = findDisplayDatabaseBoundary;
        this.findDisplayOutputBoundary = findDisplayOutputBoundary;
    }

    @Override
    public void execute() {
        List<FindDisplayOutputDTO> listCriterion = findDisplayDatabaseBoundary.getAllTypeInvoice();
        findDisplayOutputBoundary.presentType(listCriterion);
    }
}
