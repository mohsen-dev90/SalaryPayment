package core;

import java.util.List;

public interface OnPaymentFinish {
    void finish(List<Account> accounts);
}
