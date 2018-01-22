package com.chenhm.rpc;

import com.chenhm.base.cache.AbstractCache;
import com.chenhm.base.util.StringUtils;

/**
 * @author chen-hongmin
 * @since 2018/1/15 14:59
 */
public class ExporterCache extends AbstractCache<Exporter> {


    @Override
    protected KeyGenerator getKeyGenerator() {

        KeyGenerator<Exporter> keyGenerator = new KeyGenerator<Exporter>() {
            //exporter key: interfaceName -> version -> group
            @Override
            protected Object generateKey(Exporter exporter) {

                StringBuilder sb = new StringBuilder(exporter.getInterfaceName());
                if (StringUtils.isNotBlank(exporter.getVersion())){
                    sb.append(":").append(exporter.getVersion());
                }
                if (StringUtils.isNotBlank(exporter.getGroup())){
                    sb.append(":").append(exporter.getGroup());
                }
                return sb.toString();
            }
        };
        return keyGenerator;
    }
}
