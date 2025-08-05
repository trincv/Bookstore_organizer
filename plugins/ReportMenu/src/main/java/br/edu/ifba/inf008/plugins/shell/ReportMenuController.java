package br.edu.ifba.inf008.plugins.shell;

import br.edu.ifba.inf008.plugins.service.ReportService;
import br.edu.ifba.inf008.plugins.view.ReportView;
import javafx.scene.layout.StackPane;

public class ReportMenuController {

    private StackPane contentPane;
    private ReportService reportService;

    public ReportMenuController(ReportService reportService) { this.reportService = reportService; }

    public void setContentPane(StackPane contentPane) { this.contentPane = contentPane; }

    public void showBookReportView() {
        contentPane.getChildren().setAll(ReportView.create(reportService));
    }
}
