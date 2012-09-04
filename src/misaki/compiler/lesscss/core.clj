(ns misaki.compiler.lesscss.core
  (:use
    [misaki.config :only [*config*]])
  (:require
    [clojure.java.io]
    [misaki.compiler.default.core]
    [misaki.config]
    [misaki.util.file])
  (:import
    [org.lesscss LessCompiler]
    [java.io File]))

(defn is-lesscss?
  [config file]
  (and (= (.getParentFile file)
          (clojure.java.io/file (:template-dir config) (:lesscss-in-dir config)))
       (misaki.util.file/has-extension? ".less" file)))

(defn compile-lesscss
  [text]
  (->
    (LessCompiler.)
    (.compile text)))

(defn compile-lesscss-file
  [config file]
  {:status true
   :filename (str (:lesscss-out-dir config)
                  (misaki.util.file/remove-extension file))
   :body (compile-lesscss (slurp file))})

(defn -extension
  []
  (cons :less (misaki.compiler.default.core/-extension)))

(defn -config
  [config]
  (merge {:lesscss-in-dir "less/"
          :lesscss-out-dir "css/"}
         (misaki.compiler.default.core/-config config)))

(defn -compile
  [config file]
  (if (is-lesscss? config file)
    (compile-lesscss-file config file)
    (misaki.compiler.default.core/-compile config file)))

(defn -main
  [& args]
  (apply misaki.server/-main args))

