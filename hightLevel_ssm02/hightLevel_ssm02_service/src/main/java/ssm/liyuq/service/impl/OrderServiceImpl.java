package ssm.liyuq.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.liyuq.dao.IOrderDao;
import ssm.liyuq.domain.Orders;
import ssm.liyuq.service.IOrderService;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Orders> findAll(int pages,int size) throws Exception {
        //参数pageNum是页码数 参数pageSize代表是每页显示条数
        PageHelper.startPage(pages,size);
        return orderDao.findAll();

    }

    @Override
    public Orders findById(String ordersId) throws Exception {
        return orderDao.findById(ordersId);
    }
}
