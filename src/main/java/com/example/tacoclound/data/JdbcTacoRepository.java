package com.example.tacoclound.data;

import com.example.tacoclound.Ingredient;
import com.example.tacoclound.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        Long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }
        return null;
    }

    private void saveIngredientToTaco(Ingredient ingredient, Long tacoId) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient)" + "values (?, ?)",
                tacoId, ingredient.getId());
    }

    private Long saveTacoInfo(Taco taco) {
        taco.setCreateAt(new Date());
//        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
//                "insert into Taco (name, CREATEAT) values (?, ?)",
//                Types.VARCHAR, Types.TIMESTAMP
//        ).newPreparedStatementCreator(
//                Arrays.asList(
//                        taco.getName(),
//                        new Timestamp(taco.getCreateAt().getTime())
//                )
//        );
        // cai phia tren la cua sach, run khi submit se bi loi nullpointer, con cai phia duoi la cach fix
        // chua hieu van de o dau
        //https://github.com/habuma/spring-in-action-5-samples/issues/40
        // https://github.com/habuma/spring-in-action-5-samples/issues/25
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Taco (name, CREATEAT) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(Boolean.TRUE);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                taco.getName(),
                new Timestamp(taco.getCreateAt().getTime())
        ));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

}
