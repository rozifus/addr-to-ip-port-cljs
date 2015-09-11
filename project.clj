(defproject addr-to-ip-port-cljs "0.0.1"
  :description "addr-to-ip-port ported to cljs for fun and learning"
  :url "https://github.com/rozifus/addr-to-ip-port-cljs"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"
            :key "mit"
            :year 2015}
  :min-lein-version "2.3.4"

  ;; We need to add src/cljs too, because cljsbuild does not add its
  ;; source-paths to the project source-paths
  :source-paths ["src/clj" "src/cljs"]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild
  {:builds {;; This build is only used for including any cljs source
            ;; in the packaged jar when you issue lein jar command and
            ;; any other command that depends on it
            :addr-to-ip-port-cljs
            {:source-paths ["src/cljs"]
             ;; The :jar true option is not needed to include the CLJS
             ;; sources in the packaged jar. This is because we added
             ;; the CLJS source codebase to the Leiningen
             ;; :source-paths
             ;:jar true
             ;; Compilation Options
             :compiler
             {:output-to "dev-resources/public/js/addr_to_ip_port_cljs.js"
              :optimizations :advanced
              :pretty-print false}}}})
