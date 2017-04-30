package brad.util.sys;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarClassloader extends URLClassLoader implements FileFilter {

    public JarClassloader(URL urls[]) {
        super(urls);
    }

    public void addJarFile(File jarFile) throws IOException {
        if (accept(jarFile))
            addURL(jarFile.toURI().toURL());
    }

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().endsWith(".jar");
    }
}
