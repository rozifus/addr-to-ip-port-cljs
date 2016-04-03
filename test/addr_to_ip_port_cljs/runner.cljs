(ns addr-to-ip-port-cljs.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [addr-to-ip-port-cljs.core-test]))

(doo-tests 'addr-to-ip-port-cljs.core-test)
