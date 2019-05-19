package lk.ijse.librarymanagementsystem.business;

import lk.ijse.librarymanagementsystem.business.custom.impl.*;
import lk.ijse.librarymanagementsystem.dao.custom.impl.Book_ReturnsDAOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        switch (boType) {
            case  STUDENT:
                return (T) new StudentBOImpl();
            case BOOK:
                return (T) new BookBOImpl();
            case PASSWORD:
                return (T) new PasswordBOImpl();
            case BOOK_BORROWS:
                return (T) new Book_BorrowsBOImpl();
            case BOOK_RETURNS:
                return (T) new Book_ReturnBOImpl();
            default:
                return null;
        }
    }

}
