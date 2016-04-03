;;; This namespace is used for testing purpose. It use the
;;; clojurescript.test lib.
(ns addr-to-ip-port-cljs.core-test
  (:require [cljs.test :refer-macros [deftest testing are is run-tests]]
            [addr-to-ip-port-cljs.core :as atip :refer (addr-to-ip-port)]))

(deftest addr-to-ip-port-cljs-test
  (testing "Main\n"
    (testing "Basics\n"
      (let [[ip port :as pair] (addr-to-ip-port "1.2.3.4:1000")]
        (testing "pair"
          (is (= (type pair) (type (array))))
          (is (= (count pair) 2)))
        (testing "ip"
          (is (= ip "1.2.3.4")))
        (testing "port"
          (is (= port 1000))))
        (testing "size"
          (is (= atip/cache-size 1))))
    (testing "Reset\n"
      (atip/reset)
      (is (= atip/cache-size 0)))
    (testing "Large Fill\n"
      (testing "cache near full"
        (atip/reset)
        (let [target (dec atip/cache-limit)] 
          (dotimes [n target]
            (addr-to-ip-port (str "1.2.3." n ":1000")))
          (is (= atip/cache-size target))))
      (testing "cache full"
        (atip/reset)
        (let [target atip/cache-limit] 
          (dotimes [n target]
            (addr-to-ip-port (str "1.2.3." n ":1000")))
          (is (= atip/cache-size atip/cache-limit))))
      (testing "cache full plus one"
        (atip/reset)
        (let [target (inc atip/cache-limit)] 
          (dotimes [n target]
            (addr-to-ip-port (str "1.2.3." n ":1000")))
          (is (= atip/cache-size 1))))
      (testing "two cache full plus one"
        (atip/reset)
        (let [target (inc (* 2 atip/cache-limit))] 
          (dotimes [n target]
            (addr-to-ip-port (str "1.2.3." n ":1000")))
          (is (= atip/cache-size 1)))))))





        
