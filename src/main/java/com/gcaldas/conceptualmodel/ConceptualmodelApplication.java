package com.gcaldas.conceptualmodel;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.hibernate.sql.ordering.antlr.OrderingSpecification.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gcaldas.conceptualmodel.domain.Address;
import com.gcaldas.conceptualmodel.domain.Category;
import com.gcaldas.conceptualmodel.domain.City;
import com.gcaldas.conceptualmodel.domain.Client;
import com.gcaldas.conceptualmodel.domain.OrderItem;
import com.gcaldas.conceptualmodel.domain.Payment;
import com.gcaldas.conceptualmodel.domain.PaymentWithBoleto;
import com.gcaldas.conceptualmodel.domain.PaymentWithCard;
import com.gcaldas.conceptualmodel.domain.Product;
import com.gcaldas.conceptualmodel.domain.PurcharseOrder;
import com.gcaldas.conceptualmodel.domain.State;
import com.gcaldas.conceptualmodel.domain.enums.ClientType;
import com.gcaldas.conceptualmodel.domain.enums.PaymentStatus;
import com.gcaldas.conceptualmodel.repositories.AddressRepository;
import com.gcaldas.conceptualmodel.repositories.CategoryRepository;
import com.gcaldas.conceptualmodel.repositories.CityRepository;
import com.gcaldas.conceptualmodel.repositories.ClientRepository;
import com.gcaldas.conceptualmodel.repositories.OrderItemRepository;
import com.gcaldas.conceptualmodel.repositories.PaymentRepository;
import com.gcaldas.conceptualmodel.repositories.ProductRepository;
import com.gcaldas.conceptualmodel.repositories.PurcharseOrderRepository;
import com.gcaldas.conceptualmodel.repositories.StateRepository;

@SpringBootApplication
public class ConceptualmodelApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PurcharseOrderRepository purcharseOrderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(ConceptualmodelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Bed & Bath");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decor");
		Category cat7 = new Category(null, "Perfumery");
		

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mousepad", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "Sao Paulo");

		City c1 = new City(null, "Uberlandia", state1);
		City c2 = new City(null, "Sao Paulo", state2);
		City c3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(c1));
		state2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.NATURALPERSON);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);

		Address ad2 = new Address(null, "Avenida Matos", "150", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddress().addAll(Arrays.asList(ad1, ad2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		PurcharseOrder po1 = new PurcharseOrder(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		PurcharseOrder po2 = new PurcharseOrder(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);

		Payment pay1 = new PaymentWithCard(null, PaymentStatus.PAID, po1, 6);
		po1.setPayment(pay1);

		Payment pay2 = new PaymentWithBoleto(null, PaymentStatus.PENDING, po2, sdf.parse("20/10/2017 00:00"), null);
		po2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(po1, po2));

		purcharseOrderRepository.saveAll(Arrays.asList(po1, po2));

		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem oi1 = new OrderItem(po1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(po1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(po2, p2, 0.00, 1, 800.00);

		po1.getItems().addAll(Arrays.asList(oi1, oi2));
		po2.getItems().addAll(Arrays.asList(oi3));

		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
