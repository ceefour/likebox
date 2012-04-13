package com.hendyirawan.likebox;

import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hendyirawan.likebox.app.PersonV;
import com.hendyirawan.likebox.dao.LikeDao;

/**
 * Test {@link LikeDao}.
 * @author atang, ceefour
 */
@RunWith(Arquillian.class)
public class LikeDaoTest {
	//* kelas logger digunakan untuk membuat logger
	private transient Logger log = LoggerFactory.getLogger(LikeDaoTest.class);
	private LikeDao likeDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		likeDao = new LikeDao();
		likeDao.setUri("http://localhost:7474/db/data");
		likeDao.setPrefix("likebox");
		likeDao.init();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		likeDao.destroy();
	}

	@Test public void personShouldExist() {
		PersonV person = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
		Assert.assertNotNull(person);
		Assert.assertEquals("Hendy Irawan", person.getName());
	}

	@Test public void listPersonLikeArticles() {
		PersonV person = likeDao.getPerson("hendy", "hendy", "Hendy Irawan", "hendy_irawan");
	}

}
