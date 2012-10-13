(ns misaki.compiler.lesscss.core
  (:use
    [misaki.util.file :only [has-extension? remove-extension]])
  (:require
    [clojure.java.io :as cji]
    [misaki.server])
  (:import
    [org.lesscss LessCompiler]))

(defn is-lesscss?
  [config file]
  (and (= (.getParentFile file)
          (cji/file (:template-dir config)
                    (:lesscss-in-dir config)))
       (has-extension? ".less" file)))

(defn compile-lesscss
  [text]
  (->
    (LessCompiler.)
    (.compile text)))

(defn compile-lesscss-file
  [config file]
  {:status true
   :filename (str (:lesscss-out-dir config)
                  (remove-extension file))
   :body (compile-lesscss (slurp file))})

(defn -extension
  []
  [:less])

(defn -config
  [config]
  (merge {:lesscss-in-dir "less/"
          :lesscss-out-dir "css/"}
         config))

(defn -compile
  [config file]
  (when (is-lesscss? config file)
    (compile-lesscss-file config file)))

(defn -main
  [& args]
  (apply misaki.server/-main args))

