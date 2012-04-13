package com.hendyirawan.likebox;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hendyirawan.likebox.app.ArticleV;
import com.hendyirawan.likebox.app.PersonV;
import com.hendyirawan.likebox.dao.LikeDao;

/**
 * Test {@link LikeDao}.
 * @author atang, ceefour
 */
@RunWith(Arquillian.class)
public class LikeDaoTest {
	/**
	 * kelas logger digunakan untuk membuat logger
	 */
	private transient Logger log = LoggerFactory.getLogger(LikeDaoTest.class);
	private LikeDao likeDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before public void setUp() throws Exception {
		likeDao = new LikeDao();
		likeDao.setUri("http://localhost:7474/db/data");
		likeDao.setPrefix("likebox");
		likeDao.init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After public void tearDown() throws Exception {
		likeDao.destroy();
	}

	@Test public void personShouldExist() {
		PersonV person = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
		Assert.assertNotNull(person);
		Assert.assertEquals("Hendy Irawan", person.getName());
	}

	@Test public void articleShouldExist() {
		ArticleV article = likeDao.getArticle("air_itu_sehat", "air-itu-sehat", "Air Itu Sehat Lho!", "air_itu_sehat");
		Assert.assertNotNull(article);
		Assert.assertEquals("air_itu_sehat", article.getId());
	}

	@Test public void personLikesArticle() {
		PersonV person = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
		ArticleV article = likeDao.getArticle("air_itu_sehat", "air-itu-sehat", "Air Itu Sehat Lho!", "air_itu_sehat");
		likeDao.addLike(person, article);
	}

	@Test public void peopleLikesArticle() {
		PersonV hendy = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
		PersonV rudi = likeDao.getPerson("rudi", "rudi.wijaya", "Rudi Wijaya", "rudi_wijaya");
		PersonV atang = likeDao.getPerson("atang", "atang.sutisna", "Atang Sutisna", "atang_sutisna");
		ArticleV article = likeDao.getArticle("air_itu_sehat", "air-itu-sehat", "Air Itu Sehat Lho!", "air_itu_sehat");
		
		likeDao.addLike(hendy, article);
		likeDao.addLike(rudi, article);
		likeDao.addLike(atang, article);
	}

	@Test public void hendyUnlikesArticle() {
		PersonV person = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
		ArticleV article = likeDao.getArticle("air_itu_sehat", "air-itu-sehat", "Air Itu Sehat Lho!", "air_itu_sehat");
		likeDao.removeLike(person, article);
	}

	@Test public void listPersonLikeArticles() {
		PersonV person = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
	}

}
