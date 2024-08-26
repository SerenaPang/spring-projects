package com.tucymusic.app.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucymusic.app.dao.ProductDao;
import com.tucymusic.app.model.Genre;
import com.tucymusic.app.model.Product;
import com.tucymusic.app.model.ProductType;
import com.tucymusic.app.model.User;

@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public void create(Product product) {
		System.out.println("jdbc create product");
		Product productExist = findProductById(product.getId());
		if (productExist != product) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO PRODUCT(product_id, product_type_id, name, genre_id, price) " + "VALUES(?,?,?,?,?)");
				ps.setInt(1, product.getId());
				ps.setInt(2, product.getProductType());
				ps.setString(3, product.getName());
				ps.setInt(4, product.getGenreId());
				ps.setBigDecimal(5, product.getPrice());
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
				// TODO Fix sql
				PreparedStatement ps = connection.prepareStatement(
						"UPDATE PRODUCT SET product_id=?, product_type_id=?, name=?, genre_id=?, price=? WHERE product_id=?");
				ps.setInt(1, product.getId());
				ps.setInt(2, product.getProductType());
				ps.setString(3, product.getName());
				ps.setInt(4, product.getGenreId());
				ps.setBigDecimal(5, product.getPrice());
				ps.setInt(6, product.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update product " + target.toString());
				}
			} catch (SQLException ex) {
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
			// TODO Extract the ID and set it in the Genre object
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
		System.out.println("jdbc update genre");
		Genre target = findGeneById(genre.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE Genre SET genre=? WHERE genre_id=?");
				ps.setString(1, genre.getGenre());
				ps.setInt(2, genre.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update genre " + target.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	private Genre findGeneById(int id) {
		System.out.println("jdbc find Genre by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT genre_id, genre FROM Genre WHERE genre_id =?");
			ps.setInt(1, id);
			Genre genre = new Genre();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					genre.setId(rs.getInt("genre_id"));
					genre.setGenre(rs.getString("genre"));
					genre.setId(rs.getInt("genre_id"));
					System.out.println(genre.toString());
				}
				return genre;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Genre> findAllGenres() {
		System.out.println("jdbc find all Genres");
		List<Genre> genres = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT genre_id, genre FROM Genre");
			while (rs.next()) {
				Genre genre = new Genre();
				genre.setId(rs.getInt("genre_id"));
				genre.setGenre(rs.getString("genre"));
				genre.setId(rs.getInt("genre_id"));
				genres.add(genre);
			}
			System.out.println(genres);
			return genres;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(ProductType type) {
		System.out.println("jdbc create ProductType");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUCT_TYPE(product_type_id, description) " + "VALUES(?,?)");
			ps.setInt(1, type.getId());
			ps.setString(2, type.getDescription());
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
		System.out.println("jdbc update Product Type");
		ProductType target = findProductTypeById(type.getId());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection
						.prepareStatement("UPDATE PRODUCT_TYPE SET description=? WHERE product_type_id=?");
				ps.setString(1, type.getDescription());
				ps.setInt(2, type.getId());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update PRODUCT TYPE " + target.toString());
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	private ProductType findProductTypeById(int id) {
		System.out.println("jdbc find product type by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("SELECT product_type_id, description FROM PRODUCT_TYPE WHERE product_type_id =?");
			ps.setInt(1, id);
			ProductType productType = new ProductType();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					productType.setId(rs.getInt("product_type_id"));
					productType.setDescription(rs.getString("description"));
					productType.setId(rs.getInt("product_type_id"));
					System.out.println(productType.toString());
				}
				return productType;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductType> findAllProductTypes() {
		System.out.println("jdbc find all product types");
		List<ProductType> productTypes = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT product_type_id, description FROM PRODUCT_TYPE");
			while (rs.next()) {
				ProductType productType = new ProductType();
				productType.setId(rs.getInt("product_type_id"));
				productType.setDescription(rs.getString("description"));
				productType.setId(rs.getInt("product_type_id"));
				productTypes.add(productType);
			}
			System.out.println(productTypes);
			return productTypes;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Product findProductById(int id) {
		System.out.println("jdbc find product by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT product_id, product_type_id, name, genre_id, price FROM PRODUCT WHERE product_id =?");
			ps.setInt(1, id);
			Product product = new Product();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					product.setId(rs.getInt("product_id"));
					product.setProductType(rs.getInt("product_type_id"));
					product.setName(rs.getString("name"));
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
		System.out.println("jdbc find product by genre ");
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT product_id, product_type_id, genre_id, price FROM PRODUCT WHERE genre_id =?");
			ps.setInt(1, genre.getId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					product.setProductType(rs.getInt("product_type_id"));
					product.setGenreId(rs.getInt("genre_id"));
					product.setPrice(rs.getBigDecimal("price"));
					product.setId(rs.getInt("genre_id"));
					products.add(product);
				}
				return products;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findProductsByProductType(ProductType productType) {
		System.out.println("jdbc find product by productType ");
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT product_id, product_type_id, genre_id, price FROM PRODUCT WHERE product_type_id =?");
			ps.setInt(1, productType.getId());		
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					product.setProductType(rs.getInt("product_type_id"));
					product.setGenreId(rs.getInt("genre_id"));
					product.setPrice(rs.getBigDecimal("price"));
					product.setId(rs.getInt("genre_id"));
					products.add(product);
				}
				System.out.println(products);
				return products;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> findProductsByGenreAndProductType(Genre genre, ProductType productType) {
		System.out.println("jdbc find product by productType ");
		List<Product> products = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT product_id, product_type_id, name, genre_id, price FROM PRODUCT WHERE genre_id =? AND product_type_id =?");
			ps.setInt(1, genre.getId());
			ps.setInt(2, productType.getId());	
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("product_id"));
					product.setProductType(rs.getInt("product_type_id"));
					product.setName(rs.getString("name"));
					product.setGenreId(rs.getInt("genre_id"));
					product.setPrice(rs.getBigDecimal("price"));
					product.setId(rs.getInt("genre_id"));
					products.add(product);
				}
				System.out.println(products);
				return products;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Genre findGenreById(Integer genreId) {
		System.out.println("jdbc find genre by id " + genreId);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT genre_id, genre FROM GENRE WHERE genre_id =?");
			ps.setInt(1, genreId);
			Genre genre = new Genre();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					genre.setId(rs.getInt("genre_id"));
					genre.setGenre(rs.getString("genre"));	
					genre.setId(rs.getInt("genre_id"));
					System.out.println(genre);
				}
				return genre;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public ProductType findProductTypeById(Integer typeId) {
		System.out.println("jdbc find product type by id " + typeId);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT product_type_id, description FROM PRODUCT_TYPE WHERE product_type_id =?");
			ps.setInt(1, typeId);
			ProductType productType = new ProductType();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					productType.setId(rs.getInt("product_type_id"));
					productType.setDescription(rs.getString("description"));
					productType.setId(rs.getInt("product_type_id"));
					System.out.println(productType.toString());
				}
				return productType;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
