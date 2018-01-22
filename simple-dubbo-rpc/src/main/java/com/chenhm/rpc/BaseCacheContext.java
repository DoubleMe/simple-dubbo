package com.chenhm.rpc;

/**
 * @author chen-hongmin
 * @since 2018/1/15 15:33
 */
public class BaseCacheContext {


    private static ExporterCache exporterCache = new ExporterCache();


    public  static Exporter getExporter(String key){

        Exporter exporter = exporterCache.get(key);

        return exporter;
    }

    public static void addExporter(Exporter exporter){
        exporterCache.put(exporter);
    }
}
