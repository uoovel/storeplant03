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
import net.code.station.dao.ArvestiDAO;
import net.code.station.dao.ArvestiDaoImpl;
import net.code.station.dao.ArvestiNaitDAO;
import net.code.station.dao.ArvestiNaitDaoImpl;
import net.code.station.dao.BilansihaldurDAO;
import net.code.station.dao.BilansihaldurDaoImpl;
import net.code.station.dao.ContactDAO;
import net.code.station.dao.ContactDaoImpl;
import net.code.station.dao.HindDAO;
import net.code.station.dao.HindDaoImpl;
import net.code.station.dao.JaamaVoimsusDAO;
import net.code.station.dao.JaamaVoimsusDaoImpl;
import net.code.station.dao.KasutajaRollisDAO;
import net.code.station.dao.KasutajaRollisDaoImpl;
import net.code.station.dao.KliendiArvestiDAO;
import net.code.station.dao.KliendiArvestiDaoImpl;
import net.code.station.dao.PakkumineDAO;
import net.code.station.dao.PakkumineDaoImpl;
import net.code.station.dao.PerioodDAO;
import net.code.station.dao.PerioodDaoImpl;
import net.code.station.dao.ReegelDAO;
import net.code.station.dao.ReegelDaoImpl;
import net.code.station.dao.StaatusDAO;
import net.code.station.dao.StaatusDaoImpl;
import net.code.station.dao.TarbitudEnergiaDAO;
import net.code.station.dao.TarbitudEnergiaDaoImpl;
import net.code.station.dao.TellimusDAO;
import net.code.station.dao.TellimusDaoImpl;
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
    PakkumineDAO getPakkumineDAO() {
		PakkumineDAO dao = new PakkumineDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public TellimusDAO getTellimusDAO() {
		TellimusDAO dao = new TellimusDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public ArvestiNaitDAO getArvestiNaitDAO() {
		ArvestiNaitDAO dao = new ArvestiNaitDaoImpl(getDataSource());
		return dao;
	}
	@Bean
	public KliendiArvestiDAO getKliendiArvestiDAO() {
		KliendiArvestiDAO dao = new KliendiArvestiDaoImpl(getDataSource());
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
	public TarbitudEnergiaDAO getTarbitudEnergiaDAO() {
		return new TarbitudEnergiaDaoImpl(getDataSource());
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
	public ArvestiDAO getArvestiDAO() {
		return new ArvestiDaoImpl(getDataSource());
	}
	@Bean
	public ReegelDAO getReegelDAO() {
		return new ReegelDaoImpl(getDataSource());
	}
	@Bean
	public BilansihaldurDAO getBilansihaldurDAO() {
		return new BilansihaldurDaoImpl(getDataSource());
	}

}
