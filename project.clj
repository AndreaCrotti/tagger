(defproject tagger "0.1.0-SNAPSHOT"
  :description "Simplify tagging"
  :url "https://github.com/AndreaCrotti/tagger"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.10.0" :scope "provided"]
                 [andreacrotti/semver "0.2.1"]]
  :repl-options {:init-ns tagger.core})
