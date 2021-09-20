package lms.service;

abstract class MenuItem {
    protected abstract String getLabel();
    
    protected abstract void action();
}
