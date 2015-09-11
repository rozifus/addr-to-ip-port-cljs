;;; If this namespace requires macros, remember that ClojureScript's
;;; macros are written in Clojure and have to be referenced via the
;;; :require-macros directive where the :as keyword is required, while in Clojure is optional. Even
;;; if you can add files containing macros and compile-time only
;;; functions in the :source-paths setting of the :builds, it is
;;; strongly suggested to add them to the leiningen :source-paths.
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


