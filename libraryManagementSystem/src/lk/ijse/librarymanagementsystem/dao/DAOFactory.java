package lk.ijse.librarymanagementsystem.dao;

import lk.ijse.librarymanagementsystem.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        if (daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType){
        switch (daoType){
            case BOOK:
                return (T) new BookDAOImpl();
            case BOOK_BORROWS:
                return (T) new Book_BorrowsDAOImpl();
            case BOOK_RETURNS:
                return (T) new Book_ReturnsDAOImpl();
            case PASSWORD:
                return (T) new PasswordDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case QUERRY:
                return (T) new QuerryDAOImpl();
            default:
                return null;
        }
    }

}
