package cafe.interfaces;

import java.time.*;
import java.util.*;
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
import cafe.model.entities.*;

public interface iOrmMethods {
	
	// == ADD METHODS ==
	boolean addSupplier(SupplierData data);
	boolean addProduct(ProductData product);
	boolean addInvoice(InvoiceData data);
	boolean addRole(String newRole);
	boolean addItemQuantity(ItemQuantityData data);
	boolean addCafeOrder(CafeOrderData cafeOrderData);
	boolean addCalculation(CalculationData calculationData);
	boolean addMenuItem(MenuItemData data);
	boolean addMenuGroup(MenuGroupData data);
	boolean addClient(ClientData data);
	boolean addOrderStatus(String newStatus);
	boolean addTablePlace(TablePlaceData data);
	boolean addStaff(StaffData data);
	boolean addHappyHours(HappyHoursData happyHoursData);

	// == UPDATE METHODS ==
	boolean updateSupplier(SupplierData data);
	boolean updateProduct(ProductData product);
	boolean updateInvoice(InvoiceData data);
	//boolean updateRole(String newRole);
	boolean updateItemQuantity(ItemQuantityData data);
	boolean updateCafeOrder(CafeOrderData cafeOrderData);
	boolean updateCalculation(CalculationData calculationData);
	boolean updateMenuItem(MenuItemData data);
	boolean updateMenuGroup(MenuGroupData data);
	boolean updateClient(ClientData data);
	//boolean updateOrderStatus(String newStatus);
	boolean updateTablePlace(TablePlaceData data);
	boolean updateStaff(StaffData data);
	boolean updateHappyHours(HappyHoursData happyHoursData);

	
	
	// == DELETE METHODS ==

	boolean deleteSupplierById(int id);
	boolean deleteProductById(String productName);
	boolean deleteInvoiceById(long id);
	boolean deleteRoleById(String role);
	boolean deleteItemQuantityById(long id);
	boolean deleteCafeOrderById(long id);
	boolean deleteCalculationById(long id);
	boolean deleteMenuItemById(String menuItem);
	boolean deleteMenuGroupById(int id);
	boolean deleteClientById(int client);
	boolean deleteOrderStatusById(String orderStatus);
	boolean deleteTablePlaceById(int id);
	boolean deleteStaffById(String name);
	boolean deleteHappyHoursById(long id);

	// == GET METHODS ==
	Supplier getSupplierById(int id);
	Product getProductById(String productName);
	Invoice getInvoiceById(long id);
	Role getRoleById(String role);
	ItemQuantity getItemQuantityById(long id);
	CafeOrder getCafeOrderById(long id);
	Calculation getCalculationById(long id);
	MenuItem getMenuItemById(String menuItem);
	MenuGroup getMenuGroupById(int id);
	Client getClientById(int id);
	OrderStatus getOrderStatusById(String orderStatus);
	TablePlace getTablePlaceById(int id);
	Staff getStaffById(String name);
	HappyHours getHappyHoursById(long id);
	Iterable<ItemQuantity> getOrderItemsByOrderId(long id);

	
	// == GET ALL ==
	Iterable<Supplier> getAllSuppliers();
	Iterable<Product> getAllProducts();	
	Iterable<Invoice> getAllInvoices();
	Iterable<Role> getAllRoles();
	Iterable<ItemQuantity> getAllItemQuantities();
	Iterable<CafeOrder> getAllCafeOrders();
	Iterable<Calculation> getAllCalculations();
	Iterable<MenuItem> getAllMenuItems();
	Iterable<MenuGroup> getAllMenuGroups();
	Iterable<Client> getAllClients();
	Iterable<OrderStatus> getAllOrderStauses();
	Iterable<TablePlace> getAllTablePlsaces();
	Iterable<Staff> getAllStaff();
	Iterable<HappyHours> getAllHappyHours();

	
	// == SET PARAMETERS ==
	boolean setClientToOrder(long orderId, int clientId);
	boolean setNGuestsToOrder(long orderId, int nGuests);
	boolean setOrderStatus(long orderId, String status);
	boolean setDiscountToOrder(long orderId, int discount);
	boolean setPaymentMethodToOrder(long orderId, boolean byCash);

	// == REPORTS ==

	double getProfitByPeriodInAllPayments(LocalDateTime from, LocalDateTime to);
	double getProfitByPeriodInCash(LocalDateTime from, LocalDateTime to);
	double getProfitByPeriodInCreditCards(LocalDateTime from, LocalDateTime to);

	Map<Staff, Double> getProfitFromStaffByPeriod(LocalDateTime from, LocalDateTime to);
	Map<TablePlace, Double> getProfitFromTablesByPeriod(LocalDateTime from, LocalDateTime to);
	Map<Product, Map<Integer, Double>> getProductSalesByPeriod(LocalDateTime from, LocalDateTime to);
	List<Product> getProductsOnWarehouse();
	double getAvgCheckByPeriod(LocalDateTime from, LocalDateTime to);
	Map<Invoice, Double> getDebtForInvoicesByPeriod(LocalDateTime from, LocalDateTime to);
	Map<Invoice, Double> getPaymentsForInvoicesByPeriod(LocalDateTime from, LocalDateTime to);

}
