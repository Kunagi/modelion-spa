(ns modelion-spa.ui-components.commons
  (:require
   [re-frame.core :as rf]
   ["@material-ui/core" :as mui]))


(defn Label
  [text]
  ;; [:div
  ;;  {:style {:font-color :grey
  ;;           :font-weight :normal
  ;;           :font-style :italic}}
  [:> mui/Typography
   {:variant :caption}
   (str text)])
