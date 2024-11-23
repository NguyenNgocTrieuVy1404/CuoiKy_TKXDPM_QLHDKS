import java.sql.SQLException;

import javax.swing.UIManager;

import addInvoiceDisplayKS.UI.AddDisplayController;
import addInvoiceDisplayKS.UI.AddDisplayPresenter;
import addInvoiceDisplayKS.UI.AddDisplayView;
import addInvoiceDisplayKS.UI.AddViewModel;
import addInvoiceDisplayKS.UseCase.AddDisplayInputBoundary;
import addInvoiceDisplayKS.UseCase.AddDisplayOutputBoundary;
import addInvoiceDisplayKS.UseCase.AddDisplayUseCase;
import addInvoiceKS.Database.AddInvoiceDAOMySQL;
import addInvoiceKS.Database.ConnectionDB;
import addInvoiceKS.UseCase.AddInvoiceDatabaseBoundary;
import addInvoiceKS.UseCase.AddInvoiceInputBoundary;
import addInvoiceKS.UseCase.AddInvoiceOutputBoundary;
import addInvoiceKS.UseCase.AddInvoiceUseCase;
import addInvoiceKS.UseCase.DataMessageAdd;
import addInvoiceKS.UseCase.ResponseDataAdd;
import avgAmountDisplayKS.Database.AvgAmountDisplayDAOMySQL;
import avgAmountDisplayKS.Database.ConnectionDBAvg;
import avgAmountDisplayKS.UI.AvgAmountDisplayController;
import avgAmountDisplayKS.UI.AvgAmountDisplayPresenter;
import avgAmountDisplayKS.UI.AvgAmountDisplayView;
import avgAmountDisplayKS.UI.AvgAmountDisplayViewModel;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayDatabaseBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayInputBoundary;
import avgAmountDisplayKS.UseCase.AvgAmountDisplayUseCase;
import avgAmountItemKS.Database.AverageAmountDAOMySQL;
import avgAmountItemKS.Database.ConnectionDBAmount;
import avgAmountItemKS.Entity.AverageAmount;
import avgAmountItemKS.Entity.AverageAmountImpl;
import avgAmountItemKS.UseCase.AverageAmountDatabaseBoundary;
import avgAmountItemKS.UseCase.AverageAmountInputBoundary;
import avgAmountItemKS.UseCase.AverageAmountOutputBoundary;
import avgAmountItemKS.UseCase.AverageAmountUseCase;
import deleteInvoiceDisplayKS.Database.ConnectionDBDDisplay;
import deleteInvoiceDisplayKS.Database.DeleteDisplayDAOMySQL;
import deleteInvoiceDisplayKS.UI.DeleteDisplayController;
import deleteInvoiceDisplayKS.UI.DeleteDisplayPresenter;
import deleteInvoiceDisplayKS.UI.DeleteDisplayView;
import deleteInvoiceDisplayKS.UI.DeleteViewModel;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayDatabaseBoundary;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayInputBoundary;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayOutputBoundary;
import deleteInvoiceDisplayKS.UseCase.DeleteDisplayUseCase;
import deleteInvoiceKS.Database.ConnectionDBDelete;
import deleteInvoiceKS.Database.DeleteInvoiceDAOMySQL;
import deleteInvoiceKS.UseCase.DataMessageDelete;
import deleteInvoiceKS.UseCase.DeleteInvoiceDatabaseBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceInputBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceOutputBoundary;
import deleteInvoiceKS.UseCase.DeleteInvoiceUseCase;
import deleteInvoiceKS.UseCase.ResponseDataDelete;
import editInvoiceDisplayKS.UI.EditDisplayPresenter;
import editInvoiceDisplayKS.UI.EditDisplayView;
import editInvoiceDisplayKS.UI.EdittDisplayController;
import editInvoiceDisplayKS.UI.EditViewModel;
import editInvoiceDisplayKS.UseCase.EditDisplayInputBoundary;
import editInvoiceDisplayKS.UseCase.EditDisplayOutputBoundary;
import editInvoiceDisplayKS.UseCase.EditDisplayUseCase;
import editInvoiceKS.Database.ConnectionDBEdit;
import editInvoiceKS.Database.EditInvoiceDAOMySQL;
import editInvoiceKS.UseCase.DataMessageEdit;
import editInvoiceKS.UseCase.EditInvoiceDatabaseBoundary;
import editInvoiceKS.UseCase.EditInvoiceInputBoundary;
import editInvoiceKS.UseCase.EditInvoiceOutputBoundary;
import editInvoiceKS.UseCase.EditInvoiceUseCase;
import editInvoiceKS.UseCase.ResponseDataEdit;
import findInvoiceDisplayKS.Database.ConnectionDBFind;
import findInvoiceDisplayKS.Database.FindDisplayDAOMySQL;
import findInvoiceDisplayKS.UI.FindDisplayInvoiceController;
import findInvoiceDisplayKS.UI.FindDisplayInvoicePresenter;
import findInvoiceDisplayKS.UI.FindDisplayInvoiceView;
import findInvoiceDisplayKS.UI.FindViewModel;
import findInvoiceDisplayKS.UseCase.FindDisplayDatabaseBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayInputBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayOutputBoundary;
import findInvoiceDisplayKS.UseCase.FindDisplayUseCase;
import findInvoiceKS.Database.ConnectionDBFindInvoice;
import findInvoiceKS.Database.FindInvoiceDAOMySQL;
import findInvoiceKS.UseCase.DataMessageFind;
import findInvoiceKS.UseCase.FindInvoiceDatabaseBoundary;
import findInvoiceKS.UseCase.FindInvoiceInputBoundary;
import findInvoiceKS.UseCase.FindInvoiceOutputBoundary;
import findInvoiceKS.UseCase.FindInvoiceUseCase;
import findInvoiceKS.UseCase.ResponseDataFind;
import getAllInvoiceList.DataBase.ConnectionDBGet;
import getAllInvoiceList.DataBase.GetInvoiceListDAOMySQL;
import getAllInvoiceList.UI.GetInvoiceListPresenter;
import getAllInvoiceList.UseCase.GetInvoiceListDatabaseBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListInputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListOutputBoundary;
import getAllInvoiceList.UseCase.GetInvoiceListUseCase;
import getInvoiceType.Database.ConnectionDBType;
import getInvoiceType.Database.GetTypeDAOMySQL;
import getInvoiceType.UI.GetTypePresenter;
import getInvoiceType.UseCase.GetTypeDatabaseBoundary;
import getInvoiceType.UseCase.GetTypeInputBoundary;
import getInvoiceType.UseCase.GetTypeOutputBoundary;
import getInvoiceType.UseCase.GetTypeUseCase;
import quanLyHoaDon.UI.QuanLyController;
import quanLyHoaDon.UI.QuanLyHoaDonView;
import quanLyHoaDon.UI.QuanLyPresenter;
import quanLyHoaDon.UI.QuanLyViewModel;
import quanLyHoaDon.UseCase.QuanLyInputBoundary;
import quanLyHoaDon.UseCase.QuanLyOutputBoundary;
import quanLyHoaDon.UseCase.QuanLyUseCase;
import totalQuantityItemDisplayKS.UI.TotalQuantityDisplayController;
import totalQuantityItemDisplayKS.UI.TotalQuantityDisplayPresenter;
import totalQuantityItemDisplayKS.UI.TotalQuantityView;
import totalQuantityItemDisplayKS.UI.TotalViewModel;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayInputBoundary;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayOutputBoundary;
import totalQuantityItemDisplayKS.UseCase.TotalQuantityDisplayUseCase;
import totalQuantityItemKS.Database.ConnectionDBTotal;
import totalQuantityItemKS.Database.TotalQuantityDAOMySQL;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRules;
import totalQuantityItemKS.Entity.TotalQuantityBusinessRulesImpl;
import totalQuantityItemKS.UseCase.TotalQuantityDatabaseBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityInputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityOutputBoundary;
import totalQuantityItemKS.UseCase.TotalQuantityUseCase;

public class AppQuanLyHoaDon {
        public static void main(String[] args) throws ClassNotFoundException, SQLException {
                try {
                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                        e.printStackTrace();
                }

                // GetInvoiceList
                QuanLyViewModel quanLyViewModel = new QuanLyViewModel();
                ConnectionDBGet connectionDBGet = ConnectionDBGet.getInstance();
                GetInvoiceListDatabaseBoundary getInvoiceListDatabaseBoundary = new GetInvoiceListDAOMySQL(
                                connectionDBGet);
                QuanLyOutputBoundary quanLyOutputBoundary = new QuanLyPresenter(quanLyViewModel);
                GetInvoiceListOutputBoundary getInvoiceOutputBoundary = new GetInvoiceListPresenter();
                GetInvoiceListInputBoundary getInvoiceListInputBoundary = new GetInvoiceListUseCase(
                                getInvoiceListDatabaseBoundary, getInvoiceOutputBoundary);

                // AddDisplay (Type)
                ConnectionDBType connectionDBType = ConnectionDBType.getInstance();
                GetTypeDatabaseBoundary getTypeDatabaseBoundary = new GetTypeDAOMySQL(connectionDBType);
                GetTypeOutputBoundary getTypeOutputBoundary = new GetTypePresenter();
                GetTypeInputBoundary getTypeInputBoundary = new GetTypeUseCase(
                                getTypeDatabaseBoundary,
                                getTypeOutputBoundary);

                // AddInvoice
                ConnectionDB connectionDB = ConnectionDB.getInstance();
                ResponseDataAdd dataMessage = new DataMessageAdd();
                AddInvoiceDatabaseBoundary addInvoiceDatabaseBoundary = new AddInvoiceDAOMySQL(connectionDB);
                AddViewModel addDisplayViewModel = new AddViewModel();
                AddInvoiceOutputBoundary addInvoiceOutputBoundary = new AddDisplayPresenter(addDisplayViewModel);
                AddInvoiceInputBoundary addInvoiceInputBoundary = new AddInvoiceUseCase(
                                addInvoiceDatabaseBoundary,
                                addInvoiceOutputBoundary,
                                dataMessage);

                AddDisplayOutputBoundary addDisplayOutputBoundary = new AddDisplayPresenter(addDisplayViewModel);
                AddDisplayInputBoundary addDisplayInputBoundary = new AddDisplayUseCase(
                                getTypeInputBoundary,
                                getTypeOutputBoundary,
                                addDisplayOutputBoundary);

                // AddDisplay View
                AddDisplayController addDisplayController = new AddDisplayController(addInvoiceInputBoundary);
                AddDisplayView addDisplayView = new AddDisplayView();
                addDisplayView.setViewModel(addDisplayViewModel);
                addDisplayView.setAddDisplayController(addDisplayController);
                ((AddDisplayPresenter) addDisplayOutputBoundary).addObserver(addDisplayView);
                ((AddDisplayPresenter) addInvoiceOutputBoundary).addObserver(addDisplayView);

                // DeleteDisplay
                DeleteViewModel deleteViewModel = new DeleteViewModel();
                ConnectionDBDDisplay connectionDBDDisplay = ConnectionDBDDisplay.getInstance();
                DeleteDisplayDatabaseBoundary deleteDisplayDatabaseBoundary = new DeleteDisplayDAOMySQL(
                                connectionDBDDisplay);
                DeleteDisplayOutputBoundary deleteDisplayOutputBoundary = new DeleteDisplayPresenter(deleteViewModel);

                // Delete
                ResponseDataDelete dataMessageDelete = new DataMessageDelete();
                ConnectionDBDelete connectionDBDelete = ConnectionDBDelete.getInstance();
                DeleteInvoiceDatabaseBoundary deleteInvoiceDatabaseBoundary = new DeleteInvoiceDAOMySQL(
                                connectionDBDelete);
                DeleteInvoiceOutputBoundary deleteInvoiceOutputBoundary = (DeleteInvoiceOutputBoundary) deleteDisplayOutputBoundary;
                DeleteInvoiceInputBoundary deleteInvoiceUseCase = new DeleteInvoiceUseCase(
                                deleteInvoiceDatabaseBoundary,
                                deleteInvoiceOutputBoundary,
                                dataMessageDelete);

                DeleteDisplayController deleteDisplayController = new DeleteDisplayController(deleteInvoiceUseCase);

                DeleteDisplayInputBoundary deleteDisplayUseCase = new DeleteDisplayUseCase(
                                deleteDisplayDatabaseBoundary,
                                deleteDisplayOutputBoundary);

                DeleteDisplayView deleteDisplayView = new DeleteDisplayView();
                ((DeleteDisplayPresenter) deleteDisplayOutputBoundary).addObserver(deleteDisplayView);
                deleteDisplayView.setDeleteDisplayController(deleteDisplayController);
                deleteDisplayView.setDeleteIVModel(deleteViewModel);

                // EditDisplay
                EditViewModel editViewModel = new EditViewModel();
                EditDisplayOutputBoundary editDisplayOutputBoundary = new EditDisplayPresenter(editViewModel);

                // EditInvoice
                ResponseDataEdit dataMessageEdit = new DataMessageEdit();
                ConnectionDBEdit connectionDBEdit = ConnectionDBEdit.getInstance();
                EditInvoiceDatabaseBoundary editInvoiceDatabaseBoundary = new EditInvoiceDAOMySQL(connectionDBEdit);
                EditInvoiceOutputBoundary editInvoiceOutputBoundary = (EditInvoiceOutputBoundary) editDisplayOutputBoundary;
                EditInvoiceInputBoundary editInvoiceUseCase = new EditInvoiceUseCase(
                                editInvoiceDatabaseBoundary,
                                editInvoiceOutputBoundary,
                                dataMessageEdit);

                // EditDisplay
                EdittDisplayController editDisplayController = new EdittDisplayController(editInvoiceUseCase);
                EditDisplayInputBoundary editDisplayUseCase = new EditDisplayUseCase(
                                getInvoiceListInputBoundary,
                                getInvoiceOutputBoundary,
                                getTypeInputBoundary,
                                getTypeOutputBoundary,
                                editDisplayOutputBoundary);

                editDisplayController.setEditDisplayInputBoundary(editDisplayUseCase);
                EditDisplayView editDisplayView = new EditDisplayView();
                ((EditDisplayPresenter) editDisplayOutputBoundary).addObserver(editDisplayView);
                editDisplayView.setEdittDisplayController(editDisplayController);
                editDisplayView.setModelView(editViewModel);

                // AvgAmountDisplay
                ConnectionDBAvg connectionDBAvg = ConnectionDBAvg.getInstance();
                AvgAmountDisplayViewModel avgViewModel = new AvgAmountDisplayViewModel();
                AvgAmountDisplayPresenter avgPresenter = new AvgAmountDisplayPresenter(avgViewModel);
                AvgAmountDisplayDatabaseBoundary avgDatabaseBoundary = new AvgAmountDisplayDAOMySQL(connectionDBAvg);
                AvgAmountDisplayInputBoundary avgInputBoundary = new AvgAmountDisplayUseCase(
                                avgDatabaseBoundary,
                                avgPresenter);
                // AverageAmount
                AverageAmount averageAmount = new AverageAmountImpl();
                ConnectionDBAmount connectionDBAmount = ConnectionDBAmount.getInstance();
                AverageAmountDatabaseBoundary averageAmountDatabaseBoundary = new AverageAmountDAOMySQL(
                                connectionDBAmount);
                AverageAmountOutputBoundary averageAmountOutputBoundary = avgPresenter;
                AverageAmountInputBoundary averageAmountInputBoundary = new AverageAmountUseCase(
                                averageAmountOutputBoundary,
                                averageAmountDatabaseBoundary,
                                averageAmount);
                // AvgAmount View
                AvgAmountDisplayController avgController = new AvgAmountDisplayController(averageAmountInputBoundary);
                AvgAmountDisplayView avgView = new AvgAmountDisplayView();
                avgPresenter.addObserver(avgView);
                avgView.setController(avgController);
                avgView.setViewModel(avgViewModel);

                // FindInvoice + FindDisplay
                ConnectionDBFind connectionDBFind = ConnectionDBFind.getInstance();
                ConnectionDBFindInvoice connectionDBFindInvoice = ConnectionDBFindInvoice.getInstance();
                FindDisplayDatabaseBoundary findDisplayDAO = new FindDisplayDAOMySQL(connectionDBFind);
                FindInvoiceDatabaseBoundary findInvoiceDAO = new FindInvoiceDAOMySQL(connectionDBFindInvoice);
                FindViewModel findDisplayViewModel = new FindViewModel();
                FindDisplayOutputBoundary findDisplayPresenter = new FindDisplayInvoicePresenter(findDisplayViewModel);
                FindInvoiceOutputBoundary findInvoicePresenter = new FindDisplayInvoicePresenter(findDisplayViewModel);
                FindDisplayInputBoundary findDisplayInputBoundary = new FindDisplayUseCase(findDisplayDAO,
                                findDisplayPresenter);
                ResponseDataFind dataMessageFind = new DataMessageFind();
                FindInvoiceInputBoundary findInvoiceInputBoundary = new FindInvoiceUseCase(findInvoiceDAO,
                                findInvoicePresenter,
                                dataMessageFind);

                FindDisplayInvoiceView findDisplayInvoiceView = new FindDisplayInvoiceView();
                FindDisplayInvoiceController findDisplayInvoiceController = new FindDisplayInvoiceController(
                                findInvoiceInputBoundary);

                findDisplayInvoiceView.setFindInvoiceController(findDisplayInvoiceController);
                findDisplayInvoiceView.setViewModel(findDisplayViewModel);

                ((FindDisplayInvoicePresenter) findDisplayPresenter).addObserver(findDisplayInvoiceView);
                ((FindDisplayInvoicePresenter) findInvoicePresenter).addObserver(findDisplayInvoiceView);

                // TotalQuantity
                TotalViewModel totalViewModel = new TotalViewModel();
                TotalQuantityView totalQuantityView = new TotalQuantityView();

                ConnectionDBTotal connectionDBTotal = ConnectionDBTotal.getInstance();
                TotalQuantityDatabaseBoundary totalQuantityDAO = new TotalQuantityDAOMySQL(connectionDBTotal);
                TotalQuantityBusinessRules businessRules = new TotalQuantityBusinessRulesImpl();

                TotalQuantityOutputBoundary totalQuantityOutputBoundary = new TotalQuantityDisplayPresenter(
                                totalViewModel);
                TotalQuantityDisplayOutputBoundary totalDisplayOutputBoundary = (TotalQuantityDisplayPresenter) totalQuantityOutputBoundary;

                TotalQuantityInputBoundary totalQuantityUseCase = new TotalQuantityUseCase(
                                totalQuantityOutputBoundary,
                                totalQuantityDAO,
                                businessRules);

                TotalQuantityDisplayInputBoundary totalDisplayUseCase = new TotalQuantityDisplayUseCase(
                                getTypeInputBoundary,
                                totalDisplayOutputBoundary,
                                getTypeOutputBoundary);

                TotalQuantityDisplayController totalQuantityController = new TotalQuantityDisplayController(
                                totalQuantityUseCase);

                totalQuantityView.settDisplayController(totalQuantityController);
                totalQuantityView.setViewModel(totalViewModel);

                ((TotalQuantityDisplayPresenter) totalQuantityOutputBoundary).addObserver(totalQuantityView);

                // QuanLyUseCase Đây nhó
                QuanLyInputBoundary quanLyInputBoundary = new QuanLyUseCase(
                                quanLyOutputBoundary,
                                getInvoiceListInputBoundary,
                                getInvoiceOutputBoundary,
                                addDisplayInputBoundary,
                                deleteDisplayUseCase,
                                editDisplayUseCase,
                                avgInputBoundary,
                                findDisplayInputBoundary,
                                totalDisplayUseCase);

                QuanLyController quanLyController = new QuanLyController(quanLyInputBoundary);
                QuanLyHoaDonView introView = new QuanLyHoaDonView();

                ((QuanLyPresenter) quanLyOutputBoundary).addObserver(introView);
                ((AddDisplayPresenter) addInvoiceOutputBoundary).addObserver(introView);
                ((DeleteDisplayPresenter) deleteDisplayOutputBoundary).addObserver(introView);
                ((EditDisplayPresenter) editDisplayOutputBoundary).addObserver(introView);
                ((FindDisplayInvoicePresenter) findInvoicePresenter).addObserver(introView);

                introView.setQuanLyController(quanLyController);
                introView.setQuanLyViewModel(quanLyViewModel);
                introView.executeGetList();
        }
}
