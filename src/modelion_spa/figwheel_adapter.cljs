(ns ^:figwheel-hooks modelion-spa.figwheel-adapter
  (:require
   [modelion-spa.spa-init :as init]))


(defn ^:after-load on-figwheel-after-load []
  (.log js/console "!!! on-figwheel-after-load")
  (init/mount-app))

(on-figwheel-after-load)
