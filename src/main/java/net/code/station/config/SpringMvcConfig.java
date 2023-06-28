package net.code.station.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import net.code.station.dao.AMeetriNaitDAO;
import net.code.station.dao.AMeetriNaitDaoImpl;
import net.code.station.dao.MeterDAO;
import net.code.station.dao.MeterDaoImpl;
import net.code.station.dao.MeterStateDAO;
import net.code.station.dao.MeterStateDaoImpl;
import net.code.station.dao.EnergyProviderDAO;
import net.code.station.dao.EnergyProviderDaoImpl;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.ContactDaoImpl;
import net.code.station.dao.HindDAO;
import net.code.station.dao.HindDaoImpl;
import net.code.station.dao.JaamaVoimsusDAO;
import net.code.station.dao.JaamaVoimsusDaoImpl;
import net.code.station.dao.KasutajaRollisDAO;
import net.code.station.dao.KasutajaRollisDaoImpl;
import net.code.station.dao.ClientMeterDAO;
import net.code.station.dao.ClientMeterDaoImpl;
import net.code.station.dao.OfferDAO;
import net.code.station.dao.OfferDaoImpl;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.PerioodDaoImpl;
import net.code.station.dao.ReegelDAO;
import net.code.station.dao.ReegelDaoImpl;
import net.code.station.dao.StaatusDAO;
import net.code.station.dao.StaatusDaoImpl;
import net.code.station.dao.ConsumedEnergyDAO;
import net.code.station.dao.ConsumedEnergyDaoImpl;
import net.code.station.dao.ClientOrderDAO;
import net.code.station.dao.ClientOrderDaoImpl;
import net.code.station.dao.UserDAO;
import net.code.station.dao.UserDaoImpl;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "net.code.station")
public class SpringMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("administrator");
		dataSource.setPassword("Aprill2015+Aprill2015");
		return dataSource;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public ContactDAO getContactDAO() {
		ContactDAO dao = new ContactDaoImpl(getDataSource());
		return dao;
	}
	@Bean
    OfferDAO getOfferDAO() {
		OfferDAO dao = new OfferDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public ClientOrderDAO getClientOrderDAO() {
		ClientOrderDAO dao = new ClientOrderDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public MeterStateDAO getMeterStateDAO() {
		MeterStateDAO dao = new MeterStateDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public ClientMeterDAO getClientMeterDAO() {
		ClientMeterDAO dao = new ClientMeterDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public UserDAO getUserDAO() {
		return new UserDaoImpl(getDataSource());
	}
	@Bean
	public KasutajaRollisDAO getKasutajaRollisDAO() {
		return new KasutajaRollisDaoImpl(getDataSource());
	}
	@Bean
	public ConsumedEnergyDAO getConsumedEnergyDAO() {
		return new ConsumedEnergyDaoImpl(getDataSource());
	}
	@Bean
	public PerioodDAO getPerioodDAO() {
		return new PerioodDaoImpl(getDataSource());
	}
	@Bean
	public HindDAO getHindDAO() {
		return new HindDaoImpl(getDataSource());
	}
	@Bean
	public StaatusDAO getStaatusDAO() {
		return new StaatusDaoImpl(getDataSource());
	}
	@Bean
	public AMeetriNaitDAO getAMeetriNaitDAO() {
		return new AMeetriNaitDaoImpl(getDataSource());
	}
	@Bean
	public JaamaVoimsusDAO getJaamaVoimsusDAO() {
		return new JaamaVoimsusDaoImpl(getDataSource());
	}
	@Bean
	public MeterDAO getMeterDAO() {
		return new MeterDaoImpl(getDataSource());
	}
	@Bean
	public ReegelDAO getReegelDAO() {
		return new ReegelDaoImpl(getDataSource());
	}
	@Bean
	public EnergyProviderDAO getEnergyProviderDAO() {
		return new EnergyProviderDaoImpl(getDataSource());
	}

}
