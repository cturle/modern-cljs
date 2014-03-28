(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2069"]
                 [compojure "1.1.6"]
                 [domina "1.0.3-SNAPSHOT"]
                 [hiccups "0.2.0"]
                 [com.cemerick/valip "0.3.2"]
                 [org.clojars.magomimmo/shoreleave-remote-ring "0.3.1-SNAPSHOT"]
                 [org.clojars.magomimmo/shoreleave-remote "0.3.1-SNAPSHOT"] ]
  
  
  ;; CLJ AND CLJS source code path
  :source-paths ["src/clj" "src/cljs" "src/brepl"]

  ;; lein-cljsbuild plugin to build a CLJS project
  :plugins [[lein-cljsbuild "1.0.0"]
            [lein-ring "0.8.8"] ]  
  
  ;; cljsbuild options configuration
  :cljsbuild {:crossovers [valip.core valip.predicates modern-cljs.login.validators]
              :builds
              {:dev {;; CLJS source code path
                     :source-paths ["src/brepl" "src/cljs"]
                     
                     ;; Google Closure (CLS) options configuration
                     :compiler {;; CLS generated JS script filename
                                :output-to "resources/public/js/modern.js"
                                
                                ;; minimal JS optimization directive
                                :optimizations :whitespace
                                
                                ;; generated JS code prettyfication
                                :pretty-print true }}
               :re7 {; CLJS source code path
                     :source-paths ["src/brepl" "src/cljs"]

                     ;; Google Closure (CLS) options configuration
                     :compiler {;; CLS generated JS script filename
                                :output-to "resources/public/js/modern.js"
                                
                                ;; minimal JS optimization directive
                                :optimizations :simple
                                
                                ;; generated JS code prettyfication
                                :pretty-print false }}
               :prod {; CLJS source code path
                      :source-paths ["src/cljs"]
                      
                      ;; Google Closure (CLS) options configuration
                      :compiler {;; CLS generated JS script filename
                                 :output-to "resources/public/js/modern.js"
                                 
                                 ;; minimal JS optimization directive
                                 :optimizations :advanced
                                 
                                 ;; generated JS code prettyfication
                                 :pretty-print false }}}}
                    
  
  :ring {:handler modern-cljs.remotes/app} )

