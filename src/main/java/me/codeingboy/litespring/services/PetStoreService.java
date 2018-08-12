package me.codeingboy.litespring.services;

import me.codeingboy.litespring.dao.AccountDao;
import me.codeingboy.litespring.dao.ItemDao;

/**
 * Pet store service
 *
 * @author CodeingBoy
 * @version 1
 */
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

}
