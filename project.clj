(defproject org.clojars.bouzuya/misaki-lesscss "0.1.2"
  :description "misaki-lesscss is one of misaki's compiler plugin using 'LESS CSS Compiler for Java'."
  :url "https://github.com/bouzuya/misaki-lesscss"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.lesscss/lesscss "1.3.0"]
                 [misaki "0.2.1-beta"]]
  :main misaki.compiler.lesscss.core)
