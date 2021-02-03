(ns cljs
  (:require [cljs-dev.dev :as dev]
            [figwheel.main.api :as figwheel]
            [cljs-dev.dist-build :as dist]))

(def web-root "resources")

(def dev-compiler-opts
  (dev/figwheel-opts web-root {:main 'client-app.core}))

(def fig-config
  (dev/figwheel-config web-root))

(defn start-figwheel! []
  (->  (dev/with-devcards fig-config 'dev-cards.core)
       (dev/start-figwheel!  dev-compiler-opts)))

(defn dist-build! []
  (dist/dist-compile 'ss {}))

(defn repl []
  (figwheel/cljs-repl "dev"))

(defn -main [& args]
  (let [build (keyword (first args))]
    (case build
      :dist (dist-build!))))