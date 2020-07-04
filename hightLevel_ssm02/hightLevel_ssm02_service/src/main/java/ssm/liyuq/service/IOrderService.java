package ssm.liyuq.service;

import ssm.liyuq.domain.Orders;


import java.util.List;

/**
 * 业务层的order接口
 * */
public interface IOrderService {
    public List<Orders> findAll(int pages,int size) throws Exception;

    public Orders findById(String ordersId) throws Exception;
}
