(ns tagger.core
  (:require [clojure.java.shell :refer [sh]]
            [semver.core :as semver]))

(def increment-fn
  {"patch" semver/increment-patch
   "minor" semver/increment-minor
   "major" semver/increment-major})

(defn- get-latest-tag
  [tags]
  (first (semver/sorted tags)))

(defn- next-tag
  [mode tags]
  {:pre [(contains? (set (keys increment-fn)) mode)]}
  (let [incr (get increment-fn mode)]
    (semver/transform
     incr
     (get-latest-tag tags))))

(defn- get-valid-tags
  []
  (->> (sh "git" "tag")
       :out
       clojure.string/split-lines
       (filter semver/valid?)))

(defn -main [& [mode]]
  (let [curr-tags (get-valid-tags)
        new-tag (next-tag mode curr-tags)]

    (println "Tagging with version: " new-tag)
    (sh "git" "tag" new-tag)
    (shutdown-agents)))
