package br.edu.ifba.inf008.plugins.service;

import br.edu.ifba.inf008.plugins.dao.BookDao;
import br.edu.ifba.inf008.plugins.dao.LoanDao;
import br.edu.ifba.inf008.plugins.dao.UserDao;
import br.edu.ifba.inf008.plugins.model.Book;
import br.edu.ifba.inf008.plugins.model.BorrowedBookReportData;
import br.edu.ifba.inf008.plugins.model.Loan;
import br.edu.ifba.inf008.plugins.model.User;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    private final LoanDao loanDao;
    private final BookDao bookDao;
    private final UserDao userDao;

    public ReportService(LoanDao loanDao, BookDao bookDao, UserDao userDao) {
        this.loanDao = loanDao;
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    public List<BorrowedBookReportData> getBorrowedBooksReport() {
        List<BorrowedBookReportData> reportData = new ArrayList<>();

        // 1. Obter todos os empréstimos ativos (returnDate == null)
        List<Loan> activeLoans = loanDao.getAllActiveLoans(); 

        for (Loan loan : activeLoans) {
            // 2. Para cada empréstimo, obter o livro e o usuário
            Book book = bookDao.getBookById(loan.getBookId());
            User user = userDao.getUserById(loan.getUserId());

            // 3. Se o livro e o usuário existirem, criar o objeto de dados do relatório
            if (book != null && user != null) {
                reportData.add(new BorrowedBookReportData(
                    book.getTitle(),
                    book.getAuthor(),
                    user.getName(),
                    loan.getLoanDate()
                ));
            }
        }
        return reportData;
    }
}
