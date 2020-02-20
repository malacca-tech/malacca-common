package org.malacca.entry;

/**
 * <p>
 * Title :
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Author :chensheng 2020/2/20
 * </p>
 * <p>
 * Department :
 * </p>
 */
public interface EntryHolder<T> {
    /**
     * @param id    serviceId + entryId
     * @param entry httpEntry soapEntry poller
     * @return
     */
    void registerEntry(String id, T entry);
}
