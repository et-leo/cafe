package cafe.model.dao;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import cafe.controller.data.CafeOrderData;
import cafe.controller.data.CalculationData;
import cafe.controller.data.ClientData;
import cafe.controller.data.HappyHoursData;
import cafe.controller.data.InvoiceData;
import cafe.controller.data.ItemQuantityData;
import cafe.controller.data.MenuGroupData;
import cafe.controller.data.MenuItemData;
import cafe.controller.data.ProductData;
import cafe.controller.data.StaffData;
import cafe.controller.data.SupplierData;
import cafe.controller.data.TablePlaceData;
import cafe.interfaces.iOrmMethods;
import cafe.model.entities.*;

public class CafeOrm implements iOrmMethods {
	private static final String DEFUALT_ORDER_STATUS = "open";
	private static final Client DEFAULT_ORDER_CLIENT = null;
	private static final double DEFAULT_ORDER_CHECK = 0.00;
	private static final int DEFAULT_N_GUESTS = 0;
	private static final boolean DEFAULT_PAMENT_METHOD = true;
	@PersistenceContext(unitName = "springHibernate", type = PersistenceContextType.EXTENDED)
	EntityManager em;

	// === ADD METHODS ===
	@Override
	@Transactional
	public boolean addSupplier(SupplierData data) {
		boolean flag = false;
		if (data != null) {
			Supplier supplier = getSupplierById(data.getId());
			if (supplier == null) {
				em.persist(new Supplier(data.getId(), data.getInfo()));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addProduct(ProductData data) {
		boolean flag = false;
		if (data != null) {
			Product product = getProductById(data.getProductName());
			if (product == null) {
				em.persist(new Product(data.getProductName(), data.getUnitName()));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addInvoice(InvoiceData data) {
		boolean flag = false;
		if (data != null) {
			Supplier supplier = getSupplierById(data.getSupplier().getId());
			Product product = getProductById(data.getProduct().getProductName());
			if ((supplier != null) && (product != null)) {
				em.persist(new Invoice(product, supplier, data.getDate(), data.getPrice(), data.getAmount(),
						data.isPaid()));
				updateProductAmount(product, data.getAmount());
				updateProductAvgPrice(product, data.getAmount(), data.getPrice());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addRole(String newRole) {
		boolean flag = false;
		if (newRole != null) {
			Role role = getRoleById(newRole);
			if (role == null) {
				em.persist(new Role(newRole));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addStaff(StaffData data) {
		boolean flag = false;
		if (data != null) {
			Role role = getRoleById(data.getRole());
			Staff staff = getStaffById(data.getName());
			if ((role != null) && (staff == null)) {
				em.persist(new Staff(data.getName(), data.getPassword(), role));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addTablePlace(TablePlaceData data) {
		boolean flag = false;
		if (data != null) {
			TablePlace place = getTablePlaceById(data.getId());
			if (place == null) {
				em.persist(
						new TablePlace(data.getId(), data.getTop(), data.getLeft(), data.getWidth(), data.getHeight()));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addOrderStatus(String newStatus) {
		boolean flag = false;
		if (newStatus != null) {
			OrderStatus status = getOrderStatusById(newStatus);
			if (status == null) {
				em.persist(new OrderStatus(newStatus));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addClient(ClientData data) {
		boolean flag = false;
		if (data != null) {
			Client client = getClientById(data.getId());
			if (client == null) {
				em.persist(new Client(data.getId(), data.getName(), data.getBirthDay(), data.getDisount(),
						data.getInfo()));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addMenuGroup(MenuGroupData data) {
		boolean flag = false;
		if (data != null) {
			MenuGroup menuGroup = getMenuGroupById(data.getId());
			if (menuGroup == null) {
				em.persist(new MenuGroup(data.getId(), data.getGroupName(), data.getSubGroupName()));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addMenuItem(MenuItemData data) {
		boolean flag = false;
		MenuItem menuItem = getMenuItemById(data.getMenuItem());
		MenuGroup menuGroup = getMenuGroupById(data.getMenuGroup().getId());
		if ((menuItem == null) && (menuGroup != null)) {
			em.persist(new MenuItem(data.getMenuItem(), data.getPrice(), menuGroup));
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addCalculation(CalculationData calculationData) {
		boolean flag = false;
		if (calculationData != null) {
			Product product = getProductById(calculationData.getProduct().getProductName());
			MenuItem menuItem = getMenuItemById(calculationData.getMenuItem().getMenuItem());
			if ((product != null) && (menuItem != null)) {
				em.persist(new Calculation(product, menuItem, calculationData.getCount()));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addCafeOrder(CafeOrderData data) {
		boolean flag = false;
		if (data != null) {
			Staff staff = getStaffById(data.getStaff());
			TablePlace tablePlace = getTablePlaceById(data.getTableId());
			OrderStatus orderStatus = getOrderStatusById(DEFUALT_ORDER_STATUS);
			if ((staff != null) && (tablePlace != null) && (orderStatus != null)) {
				em.persist(new CafeOrder(staff, orderStatus, tablePlace, DEFAULT_ORDER_CLIENT, LocalDateTime.now(),
						DEFAULT_N_GUESTS, DEFAULT_ORDER_CHECK, DEFAULT_PAMENT_METHOD));
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean addItemQuantity(ItemQuantityData data) {
		boolean flag = false;
		if (data != null) {
			CafeOrder order = getCafeOrderById(data.getOrderId());
			if (order.getOrderStatus().getStatus().equals("open")) {
				MenuItem menuItem = getMenuItemById(data.getItem().getMenuItem());
				if ((order != null) && (menuItem != null)) {
					ItemQuantity iq = new ItemQuantity(menuItem, data.getQuantity(), order);
					iq.setPrice(data.getQuantity() * data.getItem().getPrice());
					iq.setFinalCost(iq.getPrice());
					checkHappyHours(menuItem, iq);
					em.persist(iq);
					order.setOrderCheck(order.getOrderCheck() + iq.getFinalCost());
					flag = true;
				}
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	private void checkHappyHours(MenuItem menuItem, ItemQuantity iq) {
		Query query = em.createQuery("SELECT i FROM HappyHours i WHERE i.menuItem = ?1");
		query.setParameter(1, menuItem);
		List<HappyHours> res = query.getResultList();
		LocalDateTime orderDateTime = iq.getOrder().getDateTime();
		double discount = 0.;
		for (HappyHours hh : res) {
			if (hh.getDiscountFrom().isBefore(orderDateTime) && (hh.getDiscountTo().isAfter(orderDateTime))) {
				discount = iq.getPrice() * hh.getDiscountPercent() / 100.0;
			}
		}
		iq.setDiscountAmount(discount);
		iq.setFinalCost(iq.getPrice() - iq.getDiscountAmount());
	}

	// updating amount in table Products
	private void updateProductAmount(Product product, int amount) {
		int newAmount = product.getBalance() + amount;
		product.setBalance(newAmount);
	}

	// updating AVG price in table Products
	private void updateProductAvgPrice(Product product, int amount, double price) {
		double invoicePrice = price / amount;
		if (product.getAvgPrice() == 0.00)
			product.setAvgPrice(invoicePrice);
		else {
			double newPrice = (product.getAvgPrice() + invoicePrice) / 2.0;
			product.setAvgPrice(newPrice);
		}
	}

	@Override
	@Transactional
	public boolean addHappyHours(HappyHoursData data) {
		boolean flag = false;
		if (data != null) {
			MenuItem menuItem = getMenuItemById(data.getMenuItemData().getMenuItem());
			if (menuItem != null) {
				HappyHours happyHour = new HappyHours(data.getDiscountFrom(), data.getDiscountTo(),
						data.getDiscountPercent(), menuItem);
				em.persist(happyHour);
				flag = true;
			}
		}
		return flag;
	}

	// == UPDATE METHODS ==
	@Override
	@Transactional
	public boolean updateSupplier(SupplierData data) {
		boolean flag = false;
		if (data != null) {
			Supplier supplier = getSupplierById(data.getId());
			if (supplier != null) {
				supplier.setId(data.getId());
				supplier.setInfo(data.getInfo());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateProduct(ProductData newData) {
		boolean flag = false;
		if (newData != null) {
			Product product = getProductById(newData.getProductName());
			if (product != null) {
				product.setUnitName(newData.getUnitName());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateInvoice(InvoiceData data) {
		boolean flag = false;
		if (data != null) {
			Invoice invoice = getInvoiceById(data.getId());
			Product product = getProductById(data.getProduct().getProductName());
			Supplier supplier = getSupplierById(data.getSupplier().getId());
			if ((invoice != null) && (product != null) && (supplier != null)) {
				invoice.setAmount(data.getAmount());
				invoice.setDate(data.getDate());
				invoice.setPaid(data.isPaid());
				invoice.setPrice(data.getPrice());
				invoice.setProduct(product);
				invoice.setSupplier(supplier);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateItemQuantity(ItemQuantityData data) {
		boolean flag = false;
		if (data != null) {
			MenuItem menuItem = getMenuItemById(data.getItem().getMenuItem());
			CafeOrder order = getCafeOrderById(data.getOrderId());
			ItemQuantity itemQuantity = getItemQuantityById(data.getQuantity());
			if ((menuItem != null) && (order != null) && (itemQuantity != null)) {
				itemQuantity.setOrder(order);
				itemQuantity.setItem(menuItem);
				itemQuantity.setQuantity(data.getQuantity());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateCafeOrder(CafeOrderData data) {
		boolean flag = false;
		if (data != null) {
			CafeOrder cafeOrder = getCafeOrderById(data.getId());
			Staff staff = getStaffById(data.getStaff());
			TablePlace tablePlace = getTablePlaceById(data.getTableId());
			if ((cafeOrder != null) && (staff != null) && (tablePlace != null)) {
				cafeOrder.setStaff(staff);
				cafeOrder.setTablePlace(tablePlace);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateCalculation(CalculationData data) {
		boolean flag = false;
		if (data != null) {
			Calculation calculation = getCalculationById(data.getId());
			Product product = getProductById(data.getProduct().getProductName());
			MenuItem menuItem = getMenuItemById(data.getMenuItem().getMenuItem());
			if ((calculation != null) && (product != null) && (menuItem != null)) {
				calculation.setMenuItem(menuItem);
				calculation.setProduct(product);
				calculation.setCount(data.getCount());
				flag = true;
			}
		}
		return flag;

	}

	@Override
	@Transactional
	public boolean updateMenuItem(MenuItemData data) {
		boolean flag = false;
		if (data != null) {
			MenuItem menuItem = getMenuItemById(data.getMenuItem());
			MenuGroup menuGroup = getMenuGroupById(data.getMenuGroup().getId());
			if ((menuItem != null) && (menuGroup != null)) {
				menuItem.setPrice(data.getPrice());
				menuItem.setMenuGroup(menuGroup);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateMenuGroup(MenuGroupData data) {
		boolean flag = false;
		if (data != null) {
			MenuGroup menuGroup = getMenuGroupById(data.getId());
			if (menuGroup != null) {
				menuGroup.setGroupName(data.getGroupName());
				menuGroup.setSubGroupName(data.getSubGroupName());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateClient(ClientData data) {
		boolean flag = false;
		if (data != null) {
			Client client = getClientById(data.getId());
			if (client != null) {
				client.setBirthDay(data.getBirthDay());
				client.setDisount(data.getDisount());
				client.setInfo(data.getInfo());
				client.setName(data.getName());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateTablePlace(TablePlaceData data) {
		boolean flag = false;
		if (data != null) {
			TablePlace tablePlace = getTablePlaceById(data.getId());
			if (tablePlace != null) {
				tablePlace.setLeftPosition(data.getLeft());
				tablePlace.setHeightPosition(data.getHeight());
				tablePlace.setTopPosition(data.getTop());
				tablePlace.setWidthPosition(data.getWidth());
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateStaff(StaffData data) {
		boolean flag = false;
		if (data != null) {
			Staff staff = getStaffById(data.getName());
			Role role = getRoleById(data.getRole());
			if ((staff != null) && (role != null)) {
				staff.setName(data.getName());
				staff.setPassword(data.getPassword());
				staff.setRole(role);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateHappyHours(HappyHoursData data) {
		boolean flag = false;
		if (data != null) {
			HappyHours happyHours = getHappyHoursById(data.getId());
			MenuItem menuItem = getMenuItemById(data.getMenuItemData().getMenuItem());
			if ((happyHours != null) && (menuItem != null)) {
				happyHours.setDiscountFrom(data.getDiscountFrom());
				happyHours.setDiscountTo(data.getDiscountTo());
				happyHours.setMenuItem(menuItem);
				happyHours.setDiscountPercent(data.getDiscountPercent());
				flag = true;
			}
		}
		return flag;
	}

	// == DELETE METHODS ==

	@Override
	@Transactional
	public boolean deleteProductById(String data) {
		boolean flag = false;
		if (data != null) {
			Product product = getProductById(data);
			if (product != null) {
				em.remove(product);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteInvoiceById(long id) {
		boolean flag = false;
		Invoice invoice = getInvoiceById(id);
		if (invoice != null) {
			em.remove(invoice);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteSupplierById(int id) {
		boolean flag = false;
		Supplier supplier = getSupplierById(id);
		if (supplier != null) {
			em.remove(supplier);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteRoleById(String data) {
		boolean flag = false;
		if (data != null) {
			Role role = getRoleById(data);
			if (role != null) {
				em.remove(role);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteItemQuantityById(long id) {
		boolean flag = false;
		ItemQuantity itemQuantity = getItemQuantityById(id);
		if (itemQuantity != null) {
			em.remove(itemQuantity);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteCafeOrderById(long id) {
		boolean flag = false;
		CafeOrder cafeOrder = getCafeOrderById(id);
		if (cafeOrder != null) {
			em.remove(cafeOrder);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteCalculationById(long id) {
		boolean flag = false;
		Calculation calculation = getCalculationById(id);
		if (calculation != null) {
			em.remove(calculation);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteMenuItemById(String data) {
		boolean flag = false;
		if (data != null) {
			MenuItem menuItem = getMenuItemById(data);
			if (menuItem != null) {
				em.remove(menuItem);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteMenuGroupById(int id) {
		boolean flag = false;
		MenuGroup menuGroup = getMenuGroupById(id);
		if (menuGroup != null) {
			em.remove(menuGroup);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteClientById(int id) {
		boolean flag = false;
		Client client = getClientById(id);
		if (client != null) {
			em.remove(client);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteOrderStatusById(String data) {
		boolean flag = false;
		if (data != null) {
			OrderStatus orderStatus = getOrderStatusById(data);
			if (orderStatus != null) {
				em.remove(orderStatus);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteTablePlaceById(int id) {
		boolean flag = false;
		TablePlace tablePlace = getTablePlaceById(id);
		if (tablePlace != null) {
			em.remove(tablePlace);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteStaffById(String name) {
		boolean flag = false;
		Staff staff = getStaffById(name);
		if (staff != null) {
			em.remove(staff);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteHappyHoursById(long id) {
		boolean flag = false;
		HappyHours happyHours = getHappyHoursById(id);
		if (happyHours != null) {
			em.remove(happyHours);
			flag = true;
		}
		return flag;
	}

	// == GET BY ID ==
	@Override
	public Supplier getSupplierById(int id) {
		return em.find(Supplier.class, id);
	}

	@Override
	public Product getProductById(String productName) {
		return em.find(Product.class, productName);
	}

	@Override
	public Invoice getInvoiceById(long id) {
		return em.find(Invoice.class, id);
	}

	@Override
	public Role getRoleById(String role) {
		return em.find(Role.class, role);
	}

	@Override
	public ItemQuantity getItemQuantityById(long id) {
		return em.find(ItemQuantity.class, id);
	}

	@Override
	public CafeOrder getCafeOrderById(long id) {
		return em.find(CafeOrder.class, id);
	}

	@Override
	public Calculation getCalculationById(long id) {
		return em.find(Calculation.class, id);
	}

	@Override
	public MenuItem getMenuItemById(String menuItem) {
		return em.find(MenuItem.class, menuItem);
	}

	@Override
	public MenuGroup getMenuGroupById(int id) {
		return em.find(MenuGroup.class, id);
	}

	@Override
	public Client getClientById(int id) {
		return em.find(Client.class, id);
	}

	@Override
	public OrderStatus getOrderStatusById(String orderStatus) {
		return em.find(OrderStatus.class, orderStatus);
	}

	@Override
	public TablePlace getTablePlaceById(int id) {
		return em.find(TablePlace.class, id);
	}

	@Override
	public Staff getStaffById(String name) {
		return em.find(Staff.class, name);
	}

	@Override
	public HappyHours getHappyHoursById(long id) {
		return em.find(HappyHours.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<ItemQuantity> getOrderItemsByOrderId(long id) {
		Query query = em.createQuery("SELECT iq FROM ItemQuantity iq WHERE iq.order.id = ?1");
		query.setParameter(1, id);
		return query.getResultList();
	}

	// == OTHER METHODS ==
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public boolean setDiscountToOrder(long orderId, int discount) {
		boolean flag = false;
		CafeOrder order = getCafeOrderById(orderId);
		if (order != null) {
			order.setOrderCheck(0.0);
			Query query = em.createQuery("SELECT iq FROM ItemQuantity iq WHERE iq.order = ?1");
			query.setParameter(1, order);
			List<ItemQuantity> orderItems = query.getResultList();
			for (ItemQuantity iq : orderItems) {
				double discountAmount = iq.getPrice() * discount / 100.0;
				iq.setDiscountAmount(discountAmount);
				iq.setFinalCost(iq.getPrice() - discountAmount);
				order.setOrderCheck(order.getOrderCheck() + iq.getFinalCost());
			}
			flag = true;
		}
		return flag;
	}

	// == SET PARAMETERS ==

	@Override
	@Transactional
	public boolean setClientToOrder(long orderId, int clientId) {
		boolean flag = false;
		CafeOrder order = getCafeOrderById(orderId);
		Client client = getClientById(clientId);
		if ((order != null) && (client != null)) {
			order.setClient(client);
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean setNGuestsToOrder(long orderId, int nGuests) {
		boolean flag = false;
		CafeOrder order = getCafeOrderById(orderId);
		if (order != null) {
			order.setnGuests(nGuests);
			flag = true;
		}
		return flag;

	}

	@Override
	@Transactional
	public boolean setOrderStatus(long orderId, String newStatus) {
		boolean flag = false;
		CafeOrder order = getCafeOrderById(orderId);
		if ((newStatus != null) && (order != null) && (!order.getOrderStatus().getStatus().equals(newStatus))) {
			OrderStatus status = getOrderStatusById(newStatus);
			if (status != null) {
				order.setOrderStatus(status);
				flag = true;
			}

			if (order.getOrderStatus().getStatus().equals("closed")) {
				debtProducts(orderId);
			}
		}
		return flag;
	}

	private void debtProducts(long orderId) {
		List<ItemQuantity> orderItems = (List<ItemQuantity>) getOrderItemsByOrderId(orderId);
		System.err.println("\n\n\n\n"+orderItems);

		for (ItemQuantity orderItem : orderItems) {
			MenuItem menuItem = orderItem.getMenuItem(); //какой пункт меню (омлет)
			int itemQuantity = orderItem.getQuantity(); // сколько штук этого пункта меню (2 шт)
			System.err.println("\n\n\n\n"+itemQuantity);
			Set<Calculation> calculations = menuItem.getCalculations(); // перечень продуктов для этого пункта меню (молоко - 100 яйцо - 2шт)
			for (Calculation calculation : calculations) {
				String productName = calculation.getProduct().getProductName(); // получили наименованияе продукта (молоко)
				Product product = getProductById(productName); // строим продукт по имени
				int currentBallance = product.getBalance(); // текущий баланс продукта на складе
				int debt = calculation.getCount() * itemQuantity; // считаем сколько отнять 
				product.setBalance(currentBallance - debt); // устанавливае новый балланс
			}
		}
	}

	@Transactional
	@Override
	public boolean setPaymentMethodToOrder(long orderId, boolean byCash) {
		boolean flag = false;
		CafeOrder order = getCafeOrderById(orderId);
		if (order != null) {
			order.setCash(byCash);
			flag = true;
		}
		return flag;
	}

	// == REPORTS ==

	@Override
	public Map<Staff, Double> getProfitFromStaffByPeriod(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<TablePlace, Double> getProfitFromTablesByPeriod(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Product, Map<Integer, Double>> getProductSalesByPeriod(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsOnWarehouse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAvgCheckByPeriod(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<Invoice, Double> getDebtForInvoicesByPeriod(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Invoice, Double> getPaymentsForInvoicesByPeriod(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	// == GET ALL ==

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Supplier> getAllSuppliers() {
		Query query = em.createQuery("SELECT s FROM Supplier s");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Product> getAllProducts() {
		Query query = em.createQuery("SELECT p FROM Product p");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Invoice> getAllInvoices() {
		Query query = em.createQuery("SELECT i FROM Invoice i");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Role> getAllRoles() {
		Query query = em.createQuery("SELECT r FROM Role r");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<ItemQuantity> getAllItemQuantities() {
		Query query = em.createQuery("SELECT iq FROM ItemQuantity iq");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<CafeOrder> getAllCafeOrders() {
		Query query = em.createQuery("SELECT co FROM CafeOrder co");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Calculation> getAllCalculations() {
		Query query = em.createQuery("SELECT c FROM Calculation c");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<MenuItem> getAllMenuItems() {
		Query query = em.createQuery("SELECT mi FROM MenuItem mi");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<MenuGroup> getAllMenuGroups() {
		Query query = em.createQuery("SELECT mg FROM MenuGroup mg");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Client> getAllClients() {
		Query query = em.createQuery("SELECT c FROM Client c");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<OrderStatus> getAllOrderStauses() {
		Query query = em.createQuery("SELECT os FROM OrderStatus os");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TablePlace> getAllTablePlsaces() {
		Query query = em.createQuery("SELECT tp FROM TablePlace tp");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Staff> getAllStaff() {
		Query query = em.createQuery("SELECT s FROM Staff s");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<HappyHours> getAllHappyHours() {
		Query query = em.createQuery("SELECT hh FROM HappyHours hh");
		return query.getResultList();
	}

	@Override
	public double getProfitByPeriodInAllPayments(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getProfitByPeriodInCash(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getProfitByPeriodInCreditCards(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

}
