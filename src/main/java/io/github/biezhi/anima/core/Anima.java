/**
 * Copyright (c) 2018, biezhi 王爵 (biezhi.me@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.biezhi.anima.core;

import io.github.biezhi.anima.enhancer.Instrumentation;
import org.sql2o.Sql2o;

/**
 * @author biezhi
 * @date 2018/3/13
 */
public class Anima {

    private Sql2o sql2o;
    private String tablePrefix = "";

    private Anima() {
    }

    public static Anima me() {
        return AnimaHolder.INSTANCE;
    }

    private static final class AnimaHolder {
        private static final Anima INSTANCE = new Anima();
    }

    public static Anima open(String url, String user, String pass) {
        String modelPath = Anima.class.getResource("/").getPath();
        new Instrumentation(modelPath).instrument();

        Sql2o sql2o = new Sql2o(url, user, pass);
        Anima anima = Anima.me();
        anima.sql2o = sql2o;
        return anima;
    }

    public String tablePrefix() {
        return this.tablePrefix;
    }

    public Anima tablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public Sql2o getCommonSql2o() {
        return sql2o;
    }

}