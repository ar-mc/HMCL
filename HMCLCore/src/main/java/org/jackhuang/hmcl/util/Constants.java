/*
 * Hello Minecraft! Launcher.
 * Copyright (C) 2018  huangyuhui <huanghongxun2008@126.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see {http://www.gnu.org/licenses/}.
 */
package org.jackhuang.hmcl.util;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.scene.image.Image;

import org.jackhuang.hmcl.task.Schedulers;
import org.jackhuang.hmcl.util.platform.OperatingSystem;

import java.awt.*;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Constants.
 *
 * @author huangyuhui
 */
public final class Constants {

    private Constants() {
    }

    public static final Charset SYSTEM_CHARSET = Charset.forName(OperatingSystem.ENCODING);

    public static final String DEFAULT_LIBRARY_URL = "https://libraries.minecraft.net/";
    public static final String DEFAULT_VERSION_DOWNLOAD_URL = "https://s3.amazonaws.com/Minecraft.Download/versions/";
    public static final String DEFAULT_INDEX_URL = "https://s3.amazonaws.com/Minecraft.Download/indexes/";

    public static Consumer<Runnable> UI_THREAD_SCHEDULER = s -> Schedulers.computation().schedule(s::run);
    
    public static final Consumer<Runnable> SWING_UI_THREAD_SCHEDULER = s -> {
        if (EventQueue.isDispatchThread())
            s.run();
        else
            EventQueue.invokeLater(s);
    };
    
    public static final Consumer<Runnable> JAVAFX_UI_THREAD_SCHEDULER = s -> {
        if (javafx.application.Platform.isFxApplicationThread())
            s.run();
        else
            javafx.application.Platform.runLater(s);
    };

    // lazy loading
    public static final ObjectBinding<Image> DEFAULT_ICON = Bindings.createObjectBinding(() -> new Image("/assets/img/icon.png"));

    public static <T> Predicate<T> truePredicate() {
        return s -> true;
    }

    public static <T> Predicate<T> falsePredicate() {
        return s -> false;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return x -> {};
    }
}
