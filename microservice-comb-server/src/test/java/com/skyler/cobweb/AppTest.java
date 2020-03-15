package com.skyler.cobweb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * <p>
 * NB.
 * </p>
 * Created by skyler on 2019-07-29 at 19:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CombServerApplication.class})
@ActiveProfiles("dev")
@Slf4j
public class AppTest {

    protected static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
}



