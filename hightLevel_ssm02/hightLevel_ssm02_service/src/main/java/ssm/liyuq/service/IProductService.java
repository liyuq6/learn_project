package ssm.liyuq.service;

import ssm.liyuq.domain.Product;

import java.util.List;

/**
 * 业务层的product接口
 * */
public interface IProductService {
    public List<Product> findAll() throws Exception;

    public void save(Product product);
}
