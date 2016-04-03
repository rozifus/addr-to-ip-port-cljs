(defproject addr-to-ip-port-cljs "0.0.2"
  :description "addr-to-ip-port ported to cljs for fun and learning"
  :url "https://github.com/rozifus/addr-to-ip-port-cljs"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"
            :key "mit"
            :year 2015}
  :min-lein-version "2.3.4"

  ;; We need to add src/cljs too, because cljsbuild does not add its
  ;; source-paths to the project source-paths
  :source-paths ["src" "test"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.34"]]
  :plugins [[lein-cljsbuild "1.1.3"]
            [lein-npm "0.6.1"]
            [lein-doo "0.1.6"]]
  :cljsbuild {
    :builds { 
        :test {
          :source-paths ["src" "test"]
          :compiler {
            :main 'addr-to-ip-port-cljs.runner
            :optimizations :none
            :output-to "target/cljs/unit-test.js" }}}})
