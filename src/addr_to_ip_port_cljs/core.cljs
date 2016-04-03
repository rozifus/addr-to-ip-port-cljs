
(ns addr-to-ip-port-cljs.core)

(def addr-re #"^\[?([^\]]+)\]?:(\d+)$")
(def cache-limit 100000)

(defn reset []
  (def cache (js-obj))
  (def cache-size 0))

(reset)

(defn addr-to-ip-port [addr]
  (when (>= cache-size cache-limit)
    (reset))
  (if-let [found (aget cache addr)]
    found
    (if-let [[_ ip port] (re-find addr-re addr)]
      (let [value (array ip (int port))]
        (aset cache addr value)
        (def cache-size (inc cache-size))
        value)
      (throw (js/Error. (str "invalid addr: " addr))))))


