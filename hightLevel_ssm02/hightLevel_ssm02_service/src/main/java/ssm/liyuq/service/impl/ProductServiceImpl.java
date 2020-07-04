package ssm.liyuq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssm.liyuq.dao.IProductDao;
import ssm.liyuq.domain.Product;
import ssm.liyuq.service.IProductService;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();

    }
    @Override
    public void save(Product product){
        productDao.save(product);

    }
}
