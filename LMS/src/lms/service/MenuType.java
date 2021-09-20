package lms.service;

enum MenuType {
    // General
    MAIN,
    
    // Librarian
    LIB_MAIN,
    LIB_BRANCH,
    LIB_BRANCH_UPDATE,
    LIB_BRANCH_COPIES,
    
    // Borrower
    BORR_MAIN,
    BORR_LOAN,
    BORR_LOAN_CHECKOUT,
    BORR_LOAN_RETURN,
    
    // Administrator
    ADMIN_MAIN,
    ADMIN_OPERATION,
    ADMIN_LOAN,
    //ADMIN_LOAN_BOOK, TODO implement
    //ADMIN_LOAN_BRANCH, TODO implement
    ADMIN_LOAN_BORROWER,
    ADMIN_LOAN_DUEDATE,
    ADMIN_LOAN_DUEDATE_OVERRIDE,
}
