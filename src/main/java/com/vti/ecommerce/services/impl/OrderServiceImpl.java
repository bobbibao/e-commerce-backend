package com.vti.ecommerce.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.ecommerce.domains.entities.Inventory;
import com.vti.ecommerce.domains.entities.Order;
import com.vti.ecommerce.domains.entities.OrderDetail;
import com.vti.ecommerce.domains.entities.OrderStatusHistory;
import com.vti.ecommerce.domains.entities.Product;
import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.domains.enumeration.OrderStatus;
import com.vti.ecommerce.repositories.IOrderRepository;
import com.vti.ecommerce.repositories.IOrderStatusHistoryRepository;
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

    @Autowired
    private IOrderStatusHistoryRepository orderStatusHistoryRepository;

    public OrderServiceImpl(IOrderRepository orderRepository, IUserRepository userRepository, IProductRepository productRepository, IOrderStatusHistoryRepository orderStatusHistoryRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderStatusHistoryRepository = orderStatusHistoryRepository;
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
        User user = userRepository.findById(orderDto.getUserId()).get();
        order.setOrderID(orderDto.getId());
        order.setUser(user);
        order.setOrderTotal(orderDto.getSubtotal());
        order.setOrderDate(LocalDateTime.now());

        // Set<OrderStatusHistory> orderStatusHistories = order.getOrderStatusHistory();
        Set<OrderStatusHistory> orderStatusHistories = orderStatusHistoryRepository.findByOrderId(orderDto.getId());
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setOrderStatus(OrderStatus.PROCESSING);
        orderStatusHistory.setOrder(order);
        orderStatusHistory.setChangeAt(LocalDateTime.now());
        orderStatusHistory.setChangeBy(user);
        orderStatusHistories.add(orderStatusHistory);
        order.setOrderStatusHistory(orderStatusHistories);

        order.setOrderDetails(
            orderDto.getCartItems().stream().map(
                orderDetailDto -> {
                    OrderDetail orderDetail = new OrderDetail();
                    Product product = productRepository.findById(orderDetailDto.getId()).get();
                    Inventory inventory = product.getInventory();
                    inventory.setQuantityInStock(inventory.getQuantityInStock() - orderDetailDto.getAmount());
                    inventory.setQuantitySold(inventory.getQuantitySold() + orderDetailDto.getAmount());
                    product.setInventory(inventory);
                    orderDetail.setProduct(product);
                    orderDetail.setAmount(orderDetailDto.getAmount());
                    orderDetail.setSelectedSize(orderDetailDto.getSelectedSize());
                    orderDetail.setOrder(order);
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
        return orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
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
    public List<OrderDto> getOrderByUserId(String userID) {
        return orderRepository.findByUser(userRepository.findById(userID).get()).stream().map(OrderDto::new).collect(Collectors.toList());
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
        try{
            Order order = orderRepository.findById(orderID).get();
            OrderStatus orderStatus = OrderStatus.valueOf(status);
            OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
            orderStatusHistory.setOrderStatus(orderStatus);
            orderStatusHistory.setOrder(order);
            orderStatusHistory.setChangeAt(LocalDateTime.now());
            orderStatusHistory.setChangeBy(order.getUser());
            orderStatusHistoryRepository.save(orderStatusHistory);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean createOrder(String email, Map<String, Object> newOrder) {
        try{
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(Long.valueOf(newOrder.get("productId").toString())).get();

            orderDetail.setProduct(product);
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

    private OrderDto convertToDto(Order order){
        OrderDto orderDto = new OrderDto(order);
        return orderDto;
    }

    @Override
    public OrderDto getOrderById(long orderID) {
        return new OrderDto(orderRepository.findById(orderID).get());
    }
}
