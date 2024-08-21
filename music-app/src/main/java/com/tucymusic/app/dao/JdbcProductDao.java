package com.tucymusic.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucymusic.app.model.Genre;
import com.tucymusic.app.model.Product;
import com.tucymusic.app.model.ProductType;
import com.tucymusic.app.model.User;

@Repository
public class JdbcProductDao implements ProductDao {
	@Autowired
	private JdbcDataSource dataSource;
	
	@Override
	public void create(Product product) {
		System.out.println("jdbc create product");
		Product productExist = findProductById(product.getId());
		if (productExist != product) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUCT(product_id, product_type_id, genre_id, price) " + "VALUES(?,?,?,?)");
				ps.setInt(1, product.getId());
				ps.setInt(2, product.getProductType());
				ps.setInt(3, product.getGenreId());
				ps.setBigDecimal(4, product.getPrice());
				int i = ps.executeUpdate();
				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved product info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void update(Product product) {
		System.out.println("jdbc update product");
		Product target = findProductById(product.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE PRODUCT SET name_user=? product_id, product_type_id, genre_id, price WHERE product_id=?");
				ps.setInt(1, product.getId());
				ps.setInt(2, product.getProductType());
				ps.setInt(3, product.getGenreId());
				ps.setBigDecimal(4, product.getPrice());
				ps.setInt(5, product.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update product " + target.toString());
				}
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void create(Genre genre) {
		System.out.println("jdbc create genre");
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO GENRE(genre) " + "VALUES(?)");
				ps.setString(1, genre.getGenre());
				int i = ps.executeUpdate();
				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved genre info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	@Override
	public void update(Genre genre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Genre> findAllGenres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(ProductType type) {
		System.out.println("jdbc create ProductType");
		
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUCT_TYPE(description) " + "VALUES(?)");
				ps.setString(1, type.getDescription());
				int i = ps.executeUpdate();
				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved type info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	@Override
	public void update(ProductType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductType> findAllProductTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductById(int id) {
		System.out.println("jdbc find product by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT product_id, product_type_id, genre_id, price FROM PRODUCT WHERE product_id =?");
			ps.setInt(1, id);
			Product product = new Product();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product.setId(rs.getInt("product_id"));
					product.setProductType(rs.getInt("product_type_id"));
					product.setGenreId(rs.getInt("genre_id"));
					product.setPrice(rs.getBigDecimal("price"));
					product.setId(rs.getInt("product_id"));
					System.out.println(product.toString());
				}
				return product;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findProductsByGenre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByProductType(ProductType productType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByGenreAndProductType(Genre genre, ProductType productType) {
		// TODO Auto-generated method stub
		return null;
	}

}
