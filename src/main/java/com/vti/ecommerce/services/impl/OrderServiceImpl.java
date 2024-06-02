package com.vti.ecommerce.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.OrderDetail;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.domains.enumeration.OrderStatus;
import com.vti.ecommerce.repositories.IOrderRepository;
import com.vti.ecommerce.repositories.IProductRepository;
import com.vti.ecommerce.repositories.IUserRepository;
import com.vti.ecommerce.services.IOrderService;
import com.vti.ecommerce.services.dto.OrderDto;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProductRepository productRepository;

    public OrderServiceImpl(IOrderRepository orderRepository, IUserRepository userRepository, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
//    @Override
//    public OrderDto getOrder(Long orderID) {
//        Order order = orderRepository.findById(orderID);
//        if (order == null) {
//            return null;
//        }
//        OrderDto orderDto = convertToOrderDto(order);
//        return orderDto;
//    }
//
//    @Override
//    public List<OrderDto> getOrderByUserId(String userID) {
//        List<Order> orders = orderRepository.findByUserId(userID);
//        if (orders == null) {
//            return null;
//        }
//        List<OrderDto> orderDtos = null;
//        for (Order order : orders) {
//            orderDtos.add(convertToOrderDto(order));
//        }
//        return orderDtos;
//    }
//
//    @Override
//    public boolean createOrder(OrderDto orderDto) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
//    }
//
//    @Override
//    public boolean cancelOrder(Long orderID) {
//        // TODO Auto-generated method stub
//        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
//    }
//
//    public OrderDto convertToOrderDto(Order order) {
//         OrderDto orderDto = new OrderDto();
//         orderDto.setId(order.getOrderID());
//         orderDto.setUserId(order.getUser().getOrders());
//         orderDto.setTotal(order.getOrderTotal());
//         orderDto.setOrderDetailDtos(
//                 order.getOrderDetails().stream().map(
//                         orderDetail -> {
//                             OrderDto.OrderDetailDto orderDetailDto = new OrderDto.OrderDetailDto();
//                             orderDetailDto.setProductId(orderDetail.getProduct().getOrders());
//                             orderDetailDto.setQuantity(orderDetail.getAmount());
//                             return orderDetailDto;
//                         }
//                 ).collect(Collectors.toList())
//         );
//         return orderDto;
//    }
   public Order convertToOrder(OrderDto orderDto) {
         Order order = new Order();
        order.setUser(userRepository.findById(orderDto.getUserId()).get());
        order.setOrderTotal(orderDto.getTotal());
        order.setOrderDetails(
                orderDto.getOrderDetailDtos().stream().map(
                        orderDetailDto -> {
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setOrder(order);
                            orderDetail.setProduct(productRepository.findById(orderDetailDto.getProductId()).get());
                            orderDetail.setAmount(orderDetailDto.getQuantity());
                            return orderDetail;
                        }
                ).collect(Collectors.toList())
        );
            
        return order;
   }

    @Override
    public boolean addOrder(OrderDto orderDto) {
        try{
            Order order = convertToOrder(orderDto);
            orderRepository.save(order);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderDto> getOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrders'");
    }

    @Override
    public OrderDto getOrder(Long orderID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
    }

    @Override
    public boolean removeOrder(Long orderID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrder'");
    }

    @Override
    public boolean updateOrder(Long userID, Long orderID, Map<String, Object> order) {
        return false;

    }

    @Override
    public List<Order> getOrderByUserId(String userID) {
        return this.orderRepository.getOrderByUserId(userID);
    }

    @Override
    public boolean cancelOrder(Long orderID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
    }

    @Override
    public boolean confirmOrder(Long orderID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmOrder'");
    }

    @Override
    public boolean updateOrderStatus(Long orderID, String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderStatus'");
    }
    @Override
    public boolean createOrder(String email, Map<String, Object> newOrder) {
        try{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(productRepository.findById(Long.valueOf(newOrder.get("productId").toString())).get());
            orderDetail.setAmount((Integer) newOrder.get("amount"));
            orderDetail.setSelectedSize((String) newOrder.get("size").toString());

            Order order = orderRepository.findByUserEmailAndStatus(email, OrderStatus.PENDING);
            if (order == null) {
                order = new Order();
                order.setOrderDetails(List.of(orderDetail));
            }else{
                order.getOrderDetails().add(orderDetail);
            }

            order.setUser(userRepository.findByEmail(email));

            orderDetail.setOrder(order);
            orderRepository.save(order);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
