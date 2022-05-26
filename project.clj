(defproject helix-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [thheller/shadow-cljs "2.19.0"]
                 [lilactown/helix "0.1.6"]
                 [reagent "1.1.1"]]
  :plugins [[lein-shadow "0.4.0"]]
  :repl-options {:init-ns helix-demo.core}
  :npm-deps [[react "16.8.6"]
             [react-dom "16.8.6"]]
  :shadow-cljs
  {:source-paths ["src"]
   :builds       {:app {:target     :browser
                        :output-dir "resources/public/js"
                        :asset-path "/js"
                        :modules    {:app {:entries [iku.helix.demo]}}
                        :dev        {:compiler-options {:output-feature-set :es6}}
                        :devtools   {:after-load iku.helix.demo/mount}}}
   :dev-http     {3000 {:root    "resources/public"}}})
