package deleteInvoiceDisplayKS.UseCase;

import java.util.ArrayList;
import java.util.List;

public class DeleteDisplayUseCase implements DeleteDisplayInputBoundary {
    private DeleteDisplayDatabaseBoundary deleteDisplayDatabaseBoundary;
    private DeleteDisplayOutputBoundary deleteDisplayOutputBoundary;

    public DeleteDisplayUseCase(
            DeleteDisplayDatabaseBoundary deleteDisplayDatabaseBoundary,
            DeleteDisplayOutputBoundary deleteDisplayOutputBoundary) {
        this.deleteDisplayDatabaseBoundary = deleteDisplayDatabaseBoundary;
        this.deleteDisplayOutputBoundary = deleteDisplayOutputBoundary;
    }

    @Override
    public void execute() {
        List<String> invoiceIds = deleteDisplayDatabaseBoundary.getInvoiceIds();
        String[] arrayIds = invoiceIds.toArray(new String[0]);
        List<DeleteInvoiceOutputDTO> listOutput = new ArrayList<>();
        
        for (String id : arrayIds) {
            DeleteInvoiceOutputDTO outputDTO = new DeleteInvoiceOutputDTO(arrayIds);
            listOutput.add(outputDTO);
        }
        
        deleteDisplayOutputBoundary.presentInvoiceIds(listOutput);
    }
} 