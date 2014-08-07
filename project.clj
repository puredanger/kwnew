(defproject kwnew "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[com.fasterxml.jackson.core/jackson-core "2.4.1.1"]
                 [org.clojure/clojure "1.6.0"]
;;                 [org.clojure/clojure "1.7.0-alpha1"]
                ]
  :jvm-opts ^:replace ["-server" "-Xmx256m"])
