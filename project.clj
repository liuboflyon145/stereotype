(defproject stereotype-clj "0.1.7-SNAPSHOT"
  :description "A library for setting up test data in Clojure "
  :url "https://github.com/josephwilk/stereotype-clj"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [korma "0.3.0-RC2"]]

  :min-lein-version "2.0.0"
  :profiles {:shared {:plugins [[lein-midje "3.0-beta1"]]}
             :dev [:shared {:dependencies [                                   [midje "1.5-RC1"] [bultitude "0.1.7"] [org.clojure/java.jdbc "0.2.3"] [org.xerial/sqlite-jdbc "3.7.2"] [clj-time "0.4.4"]]}]
             :1.3 [:shared {:dependencies [[org.clojure/clojure "1.3.0"]      [midje "1.5-RC1"] [bultitude "0.1.7"] [org.clojure/java.jdbc "0.2.3"] [org.xerial/sqlite-jdbc "3.7.2"] [clj-time "0.4.4"]]}]
             :1.4 [:shared {:dependencies [[org.clojure/clojure "1.4.0"]      [midje "1.5-RC1"] [bultitude "0.1.7"] [org.clojure/java.jdbc "0.2.3"] [org.xerial/sqlite-jdbc "3.7.2"] [clj-time "0.4.4"]]}]
             :1.5 [:shared {:dependencies [[org.clojure/clojure "1.5.0-RC17"] [midje "1.5-RC1"] [bultitude "0.1.7"] [org.clojure/java.jdbc "0.2.3"] [org.xerial/sqlite-jdbc "3.7.2"] [clj-time "0.4.4"]]}]})
